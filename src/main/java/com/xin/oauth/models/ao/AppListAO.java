package com.xin.oauth.models.ao;

import com.xin.oauth.models.bo.AppBO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xinyu.huang02
 * @date 2020-03-01 16:01
 * @class App list AO
 */

@Data
@Builder
public class AppListAO {

    private List<AppAO> appAOList;

    public void addApp(AppBO appBO) {
        if (appAOList == null)
            appAOList = new ArrayList<>();
        appAOList.add(AppAO.fromBO(appBO));
    }

    public void addApps(List<AppBO> appBOS) {
        if (appAOList == null)
            appAOList = new ArrayList<>();
        for (AppBO appBO : appBOS) {
            appAOList.add(AppAO.fromBO(appBO));
        }
    }
}
