package com.xin.oauth.aop;

import com.google.common.util.concurrent.RateLimiter;
import com.xin.oauth.annotation.NeedRateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xinyu.huang02
 * @date 2020-03-19 17:03
 * @class 限流相关切面
 */
@Component
@Slf4j
@Aspect
public class RateLimiterAspect extends AbstractAspect {

    /**
     * 使用url作为Key, 存放令牌桶，防止每次重新创建令牌桶
     */
    private final ConcurrentHashMap<String, RateLimiter> rateLimiterMap = new ConcurrentHashMap<>();


    @Pointcut("@annotation(com.xin.oauth.annotation.NeedRateLimiter)")
    public void rateLimiter() {

    }

    /**
     * 限流切面处理
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("rateLimiter()")
    public Object handler(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse resp = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        NeedRateLimiter rateLimit = method.getAnnotation(NeedRateLimiter.class);
        if (rateLimit != null) {
            RateLimiter rateLimiter = null;
            String url = req.getRequestURI();
            if (!rateLimiterMap.containsKey(url)) {
                rateLimiter = RateLimiter.create(rateLimit.permitsPerSecond());
                rateLimiterMap.put(url, rateLimiter);
                log.info("Request url {}, create token bucket success {}", url, rateLimiter);
            }
            rateLimiter = rateLimiterMap.get(url);
            boolean acquire = rateLimiter.tryAcquire(rateLimit.timeout(), rateLimit.timeUnit());
            if (!acquire) {
                if (null != resp)
                    responseResult(resp, 500, rateLimit.msg());
                return null;
            }
        }
        return pjp.proceed();
    }

}
