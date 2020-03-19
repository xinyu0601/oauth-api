package com.xin.oauth.aop;

import com.xin.oauth.annotation.ApiIdempotent;
import com.xin.oauth.models.request.AccessTokenRequestBody;
import com.xin.oauth.utils.RedisUtils;
import com.xin.oauth.utils.lock.DistributedLockHelper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author xinyu.huang02
 * @date 2020-03-19 11:07
 * @class Redis Lock分布式锁切面
 */
@Aspect
@Component
@Slf4j
public class ApiIdempotentAspect extends AbstractAspect {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private DistributedLockHelper distributedLockHelper;

    @Pointcut("@annotation(com.xin.oauth.annotation.ApiIdempotent)")
    private void apiIdempotent() {

    }

    /**
     * 切面编程，方法上注解RedisLock会自动进行加锁操作
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("apiIdempotent()")
    public Object arouond(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        Method method = signature.getMethod();
        ApiIdempotent apiIdempotent = method.getAnnotation(ApiIdempotent.class);
        Object[] args = pjp.getArgs();
        if (apiIdempotent != null && args.length > 0) {
            Object arg = args[0];
            if (arg instanceof AccessTokenRequestBody) {
                AccessTokenRequestBody accessTokenRequestBody = (AccessTokenRequestBody) arg;
                String key = accessTokenRequestBody.getAppKey();
                boolean lock = distributedLockHelper.lock(key, apiIdempotent.keepMills(), apiIdempotent.retryTimes(), apiIdempotent.sleepMills());
                if (!lock) {
                    log.info("get lock failed : " + key);
                    if (response != null)
                        responseResult(response, 500, apiIdempotent.msg());
                    return null;
                }
                //得到锁,执行方法，释放锁
                log.debug("get lock success : " + key);
                try {
                    return pjp.proceed();
                } catch (Exception e) {
                    log.error("execute locked method occured an exception", e);
                } finally {
                    boolean releaseResult = distributedLockHelper.releaseLock(key);
                    log.debug("release lock : " + key + (releaseResult ? " success" : " failed"));
                }
                return null;
            }
        }
        return null;
    }

}
