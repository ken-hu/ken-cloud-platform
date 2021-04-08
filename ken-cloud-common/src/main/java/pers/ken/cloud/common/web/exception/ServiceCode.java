package pers.ken.cloud.common.web.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <code>ServiceCode</code>
 * <desc>
 * 描述：
 * <desc/>
 * <b>Creation Time:</b> 2021/4/8 21:51.
 *
 * @author Ken.Hu
 */
@Getter
@AllArgsConstructor
public enum ServiceCode {
    /* 成功状态码 */
    SUCCESS(1, "成功"),

    /* 成功状态码 */
    FAIL(0, "请求失败"),

    PARAM_IS_INVALID(10001, "参数无效");


    private final int code;
    private final String msg;
}
