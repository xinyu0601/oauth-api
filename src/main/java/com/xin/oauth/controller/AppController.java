package com.xin.oauth.controller;

import com.xin.oauth.enums.ResultCodeEnum;
import com.xin.oauth.models.ao.AppAO;
import com.xin.oauth.models.ao.AppListAO;
import com.xin.oauth.models.ao.ResultAO;
import com.xin.oauth.models.bo.AppBO;
import com.xin.oauth.models.request.AppInfoRequestBody;
import com.xin.oauth.models.request.AppListRequestBody;
import com.xin.oauth.models.request.UpdateAppRequestBody;
import com.xin.oauth.service.AppService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class AppController {

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
        AppBO appBO = AppBO.fromRegisterBody(appInfoRequestBody);
        appBO = appService.registerApp(appBO);
        return new ResultAO<AppAO>(ResultCodeEnum.COMMON_SUCCESS, AppAO.fromBO(appBO));
    }


    /**
     * 根据ID删除App
     *
     * @param id
     * @return
     */
    @DeleteMapping("/remove/{id:.*}")
    @ResponseBody
    @ApiOperation(value = "根据ID删除App")
    public ResultAO<String> removeApp(@PathVariable("id") Long id) {
        appService.removeApp(id);
        return new ResultAO<>(ResultCodeEnum.COMMON_SUCCESS, String.format("Remove app [%s] success", id));
    }


    /**
     * 获取所有App信息
     *
     * @return
     */
    @PostMapping("/all")
    @ApiOperation(value = "获取所有App信息")
    public ResultAO<List<AppAO>> allApp(@RequestBody AppListRequestBody appListRequestBody) {
        Long userId = appListRequestBody.getUserId();
        List<AppBO> apps = appService.all(userId);
        AppListAO appListAO = AppListAO.builder().build();
        appListAO.addApps(apps);
        return new ResultAO<List<AppAO>>(ResultCodeEnum.COMMON_SUCCESS, appListAO.getAppAOList());
    }


    /**
     * 更新App信息
     *
     * @param id
     * @param updateAppRequestBody
     * @return
     */
    @PutMapping("/update/{id:.*}")
    @ApiOperation(value = "更新App信息")
    public ResultAO<AppAO> updateApp(@PathVariable("id") Long id,
                                     @RequestBody UpdateAppRequestBody updateAppRequestBody) {
        AppBO appBO = AppBO.fromUpdateBody(updateAppRequestBody);
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
