package com.xin.oauth.enums;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author xinyu.huang02
 * @date 2020-02-25 23:20
 * @class Result Code Enum
 */
public enum ResultCodeEnum {

    //成功
    COMMON_SUCCESS(0, "成功"),
    //失败
    COMMON_FAIL(1, "系统异常"),
    //参数错误
    PARAM_INVALID(2, "参数错误"),
    //数据不存在
    NULL_DATA(3, "数据不存在"),
    NET_BUSY(4, "网络繁忙"),
    NO_LOGIN(5, "未登录或者需要需要重新登录"),
    //获取组织结构失败
    ORG_PRC_ERROR(112001, "获取组织结构失败"),

    PS_ERROR(112002, "PS接口錯誤"),
    PATH_ERROR(112003, "访问路径或参数错误"),
    CERT_CODE_TIMEOUT(112004, "验证码已经超时"),
    CERT_CODE_INCORRECT(112005, "验证码错误"),
    CERT_FREQUENT(112006, "已经发送过验证码"),
    DIFF_PHONE(112007, "手机号不正确"),
    WX_ERROR(112008, "微信RPC失败"),
    PS_CODE_NULL(112009, "没有查询到相应的人员信息"),
    PS_DEP_CODE_NULL(112010, "部门还未创建"),
    PS_RPC_EXCEPTION(112011, "PS_RPC_EXCEPTION ps服务端出错，请追查ps日志"),
    MAIL_RPC_EXCEPTION(112012, "发送邮件失败"),
    TIME_EX(112013, "非常抱歉，已超过入职时间，暂时不能填写"),
    ERROR_ENT_TIME(112014, "入职时间不正确，请联系管理员，并提供手机号"),
    NO_AUTH(112015, "没有权限"),
    REGISTERED(112016, "已经注册过"),
    ADMIN_NO_LOGIN(112017, "管理员未登录或者需要需要重新登录"),
    PERSONAL_ERROR(112018, "获取个人加密信息失败"),
    APP_FAIL(112019, "获取app信息失败"),
    SM_RPC_EXCEPTION(112020, "发送短信失败"),
    PHONE_ERROR(112021, "手机号不一致"),
    SM_TEMPLATE_ERROR(112022, "短信模板错误"),
    SM_TEXT_ERROR(112023, "短信内容错误"),
    CRYP_ERROR(112024, "加密失败"),
    DUTY_DATA_ERROR(112025, "岗位信息失败"),
    WX_API_ERROR(60001, "missing x-uuser header"),
    NON_XUUSER_HEADER(60020, "missing x-uuser header"),
    DECODE_XUUSER_ERROR(60021, "decode x-uuser error"),
    ;

    ResultCodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @ApiModelProperty(value = "返回码")
    private Integer code;
    @ApiModelProperty(value = "返回码说明")
    private String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static String getDesc(int code) {
        String result = null;
        for (ResultCodeEnum resultCodeEnum : ResultCodeEnum.values()) {
            if (resultCodeEnum.code == code) {
                result = resultCodeEnum.desc;
            }
        }
        return result;
    }
}
