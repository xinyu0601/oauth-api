package com.xin.oauth.controller;

import com.xin.oauth.enums.ResultCodeEnum;
import com.xin.oauth.exceptions.AppException;
import com.xin.oauth.models.ao.AppAO;
import com.xin.oauth.models.ao.AppListAO;
import com.xin.oauth.models.ao.ResultAO;
import com.xin.oauth.models.bo.AppBO;
import com.xin.oauth.models.request.AppInfoRequestBody;
import com.xin.oauth.models.request.AppListRequestBody;
import com.xin.oauth.service.AppService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xinyu.huang02
 * @date 2020-02-26 12:09
 * @class App Info Controller
 */
@RestController
@RequestMapping("/app")
@Api(value = "App Controller")
public class AppController {

    private static final Logger log = LoggerFactory.getLogger(AppController.class);

    @Autowired
    private AppService appService;

    /**
     * 注册一个新的应用，注册成功后生成appkey和appSecret
     *
     * @param appInfoRequestBody
     * @return
     */
    @PostMapping("/register")
    @ApiOperation(value = "注册一个新的应用，注册成功后生成appkey和appSecret")
    public ResultAO<AppAO> registerApp(@RequestBody AppInfoRequestBody appInfoRequestBody) {
        AppBO appBO = AppBO.fromBody(appInfoRequestBody);
        try {
            appBO = appService.registerApp(appBO);
        } catch (Exception e) {
            throw new AppException("Register app error", e);
        }
        return new ResultAO<AppAO>(ResultCodeEnum.COMMON_SUCCESS, AppAO.fromBO(appBO));
    }


    /**
     * 根据ID删除App
     *
     * @param id
     * @return
     */
    @DeleteMapping("/remove/{id:.*}")
    @ApiOperation(value = "根据ID删除App")
    public ResultAO<String> removeApp(@PathVariable("id") String id) {
        appService.removeApp(id);
        return new ResultAO<>(ResultCodeEnum.COMMON_SUCCESS, String.format("Remove app [%s] success", id));
    }


    /**
     * 获取所有App信息
     *
     * @return
     */
    @GetMapping("/all")
    @ApiOperation(value = "获取所有App信息")
    public ResultAO<List<AppAO>> allApp(@RequestBody AppListRequestBody appListRequestBody) {
        String userId = appListRequestBody.getUserId();
        List<AppBO> apps = appService.all(userId);
        AppListAO appListAO = AppListAO.builder().build();
        appListAO.addApps(apps);
        return new ResultAO<List<AppAO>>(ResultCodeEnum.COMMON_SUCCESS, appListAO.getAppAOList());
    }


    /**
     * 更新App信息
     *
     * @param id
     * @param appInfoRequestBody
     * @return
     */
    @PutMapping("/update/{id:.*}")
    @ApiOperation(value = "更新App信息")
    public ResultAO<AppAO> updateApp(@PathVariable("id") String id,
                                     AppInfoRequestBody appInfoRequestBody) {
        AppBO appBO = AppBO.fromBody(appInfoRequestBody);
        appBO = appService.updateApp(id, appBO);
        return new ResultAO<AppAO>(ResultCodeEnum.COMMON_SUCCESS, AppAO.fromBO(appBO));
    }


    /**
     * 搜索App信息
     *
     * @return
     */
    @PostMapping("/search")
    @ApiOperation(value = "搜索App信息")
    public ResultAO<Object> searchApp() {
        return new ResultAO<>();
    }

}
