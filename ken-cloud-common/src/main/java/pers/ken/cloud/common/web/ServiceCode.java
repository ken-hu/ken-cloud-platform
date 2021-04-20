package pers.ken.cloud.common.web;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * <code>ServiceCode</code>
 * <desc>
 * 描述：
 * <desc/>
 * <b>Creation Time:</b> 2021/4/8 21:51.
 *
 * @author _Ken.Hu
 */
@Getter
@AllArgsConstructor
public enum ServiceCode implements Serializable {
    /* 成功状态码 */
    SUCCESS(1, "成功"),

    /* 成功状态码 */
    FAIL(0, "请求失败"),

    PARAM_IS_INVALID(10001, "参数无效"),

    PERMISSION_NOT_ENOUGH(20001, "权限不足,拒绝访问"),
    AUTHENTICATION_FAIL(20002, "鉴权失败"),
    TOKEN_INVALID(20004, "非法token,禁止访问"),
    TOKEN_CHECK_FAILED(20005, "token校验异常"),
    ;

    private final int code;
    private final String msg;
}
