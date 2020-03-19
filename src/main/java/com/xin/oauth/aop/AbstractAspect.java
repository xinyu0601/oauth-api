package com.xin.oauth.aop;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author xinyu.huang02
 * @date 2020-03-19 19:43
 * @class 抽象切面
 */
@Slf4j
public class AbstractAspect {

    /**
     * 自定义响应结果
     *
     * @param response 响应
     * @param code     响应码
     * @param message  响应信息
     */
    protected void responseResult(HttpServletResponse response, Integer code, String message) {
        response.resetBuffer();
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
            printWriter.println("{\"code\":" + code + " ,\"message\" :\"" + message + "\"}");
            response.flushBuffer();
        } catch (IOException e) {
            log.error(" response error e = {}", e.getMessage(), e);
        } finally {
            if (printWriter != null) {
                printWriter.flush();
                printWriter.close();
            }
        }
    }
}
