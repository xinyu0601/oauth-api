package com.xin.oauth.aop;

import com.xin.oauth.enums.ResultCodeEnum;
import com.xin.oauth.models.ao.ResultAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author xinyu.huang02
 * @date 2020-03-04 15:08
 * @class Global exception handler
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 全局异常拦截，封装报错信息
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResultAO<String> catchException(HttpServletRequest request, Exception exception) {
        String errCode = UUID.randomUUID().toString();
        log.error("err code : {}", errCode);
        exception.printStackTrace();
        String exceptionMsg = String.format("系统出现异常: %s， 请联系管理员解决！errorCode: %s", exception.getMessage(), errCode);
        return new ResultAO<String>(ResultCodeEnum.COMMON_FAIL, exceptionMsg);
    }

}
