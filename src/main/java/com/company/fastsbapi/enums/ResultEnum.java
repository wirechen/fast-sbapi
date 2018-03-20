package com.company.fastsbapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: WireChen
 * @Date: Created in 上午11:43 2018/2/24
 * @Description:
 */
@AllArgsConstructor
@Getter
public enum ResultEnum{

    // 统一结果返回码
    SUCCESS(0, "成功"),
    RETRY(-1, "请求失败,请稍后重试"),
    NO_VALID_PARAM(1, "请求参数有误"),
    FAIL(400, "请求失败"),
    AUTH_ERROR(403, "认证失败"),
    NO_HANDLER(404, "接口不存在"),
    SERVER_ERROR(500, "服务器内部错误"),

    // 前端业务异常返回码
    ERROR_LOGIN(10001, "登录账号或密码错误"),
    NO_EXIST_USER(10002, "用户不存在"),
    ERROR_ID(10003, "ID不存在"),
    ;

    private Integer code;

    private String message;

}
