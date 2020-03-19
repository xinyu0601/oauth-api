package com.xin.oauth.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xinyu.huang02
 * @date 2020-03-04 10:56
 * @class Log Aspect
 */

@Aspect
@Component
public class LogAspect {

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.xin.oauth.controller..*.*(..))")
    public void record() {

    }

    /**
     * 记录请求日志
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("record()")
    public void requestLog(JoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        startTime.set(System.currentTimeMillis());
        String url = request.getRequestURL().toString();
        log.info("--- URL:{} ,\n HTTP_METHOD:{} , \n IP :{} , \n COOKIE : {} , \n" +
                        " CLASS_METHOD : {} ,\n HEADER : {},\n ARGS : {} ---",
                url,
                request.getMethod(),
                request.getRemoteAddr(),
                JSON.toJSONString(request.getCookies()),
                joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(),
                JSON.toJSONString(getHeadersInfo(request)),
                Arrays.toString(joinPoint.getArgs())
        );
    }


    /**
     * 请求返回日志
     *
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "record()")
    public void doAfterReturn(Object ret) throws Throwable {
        log.info("--- RESPONSE : {}, \n COST TIME : {}", JSON.toJSONString(ret), System.currentTimeMillis() - startTime.get());
    }


    /**
     * 获取请求头信息
     *
     * @param request
     * @return
     */
    private Map<String, String> getHeadersInfo(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

}
