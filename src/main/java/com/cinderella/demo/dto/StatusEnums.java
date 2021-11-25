package com.cinderella.demo.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Cinderella
 * @time 2021/9/1 15:38
 * @Description
 **/
public enum StatusEnums {
    SUCCESS(200, "登录成功"),
    SYSTEM_ERROR(500, "系统错误"),
    ACCOUNT_UNKNOWN(500, "账户不存在"),
    ACCOUNT_IS_DISABLED(13, "账号被禁用"),
    INCORRECT_CREDENTIALS(400,"用户名或密码错误"),
    PARAM_ERROR(401, "参数错误"),
    PARAM_NULL(402, "参数为空"),
    VALIDATECODE_ERROR(403, "验证码错误"),
    PARAM_REPEAT(404, "参数已存在"),
    PERMISSION_ERROR(405, "没有操作权限"),
    NOT_LOGIN_IN(15, "账号未登录"),
    AUTH_ERROR(16, "认证失败"),
    OTHER(-100, "其他错误");

    @Getter
    @Setter
    private int code;
    @Getter
    @Setter
    private String message;

    StatusEnums(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
