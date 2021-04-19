package pers.ken.cloud.common.web;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
public enum ServiceCode {
    /* 成功状态码 */
    SUCCESS(1, "成功"),

    /* 成功状态码 */
    FAIL(0, "请求失败"),

    PARAM_IS_INVALID(10001, "参数无效"),

    PERMISSION_NOT_ENOUGH(20001, "权限不足"),
    TOKEN_ILLEGAL(20002, "非法TOKEN，禁止访问"),
    TOKEN_MISSING(20003, "未发现TOKEN"),
    ;

    private final int code;
    private final String msg;
}
