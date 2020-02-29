package com.xin.oauth.models.ao;

import com.xin.oauth.enums.ResultCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author xinyu.huang02
 * @date 2020-02-25 23:19
 * @class 统一返回数据
 */
@Data
@AllArgsConstructor
@Builder
@ApiModel(description = "统一返回数据", reference = "ResultCodeEnum", discriminator = "ResultCodeEnum")
public class ResultAO<T> {

    public ResultAO() {
    }

    public ResultAO(int code, String msg) {
        this(code, msg, null);
    }

    public ResultAO(ResultCodeEnum resultCodeEnum, T data) {
        this(resultCodeEnum.getCode(), resultCodeEnum.getDesc(), data);
    }


    public ResultAO(ResultCodeEnum resultCodeEnum) {
        this(resultCodeEnum.getCode(), resultCodeEnum.getDesc(), null);
    }

    @ApiModelProperty(value = "返回码 0-成功，1-服务错误， 其他参见后续详细错误码", dataType = "int", required = true)
    private int code;
    @ApiModelProperty(value = "返回码对应说明", dataType = "String", required = true)
    private String msg;
    @ApiModelProperty(value = "返回具体数据", allowEmptyValue = true)
    private T data;

    public static ResultAO success() {
        return new ResultAO(ResultCodeEnum.COMMON_SUCCESS.getCode(), ResultCodeEnum.COMMON_SUCCESS.getDesc(), null);
    }

    public static ResultAO success(Object object) {
        ResultAO resultAO = new ResultAO(ResultCodeEnum.COMMON_SUCCESS.getCode(), ResultCodeEnum.COMMON_SUCCESS.getDesc(), object);
        resultAO.setData(object);
        return resultAO;
    }

    public static ResultAO fail() {
        return new ResultAO(ResultCodeEnum.COMMON_FAIL.getCode(), ResultCodeEnum.COMMON_FAIL.getDesc(), null);
    }


}
