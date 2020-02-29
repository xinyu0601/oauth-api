package com.xin.oauth.controller;

import com.xin.oauth.models.ao.ResultAO;
import com.xin.oauth.models.request.AppInfoRequestBody;
import com.xin.oauth.service.AppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xinyu.huang02
 * @date 2020-02-26 12:09
 * @class App Info Controller
 */
@RestController
@RequestMapping("/app")
public class AppController {

    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    @Autowired
    private AppService appService;

    /**
     * 注册一个新的应用，注册成功后生成appkey和appSecret
     *
     * @param appInfoRequestBody
     * @return
     */
    @PostMapping("/register")
    public ResultAO<Object> registerApp(@RequestBody AppInfoRequestBody appInfoRequestBody) {
        return new ResultAO<>();
    }


    /**
     * 根据ID删除App
     *
     * @param id
     * @return
     */
    @DeleteMapping("/remove/{id:.*}")
    public ResultAO<Object> removeApp(@PathVariable("id") String id) {
        return new ResultAO<>();
    }


    /**
     * 获取所有App信息
     *
     * @return
     */
    @GetMapping("/all")
    public ResultAO<Object> allApp() {

        return new ResultAO<>();
    }


    /**
     * 更新App信息
     *
     * @param id
     * @param appInfoRequestBody
     * @return
     */
    @PutMapping("/update/{id:.*}")
    public ResultAO<Object> updateApp(@PathVariable("id") String id,
                                      AppInfoRequestBody appInfoRequestBody) {

        return new ResultAO<>();
    }


    /**
     * 搜索App信息
     *
     * @return
     */
    @PostMapping("/search")
    public ResultAO<Object> searchApp() {
        return new ResultAO<>();
    }

}
