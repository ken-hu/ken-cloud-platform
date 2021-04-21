package pers.ken.cloud.common.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

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
    SUCCESS(1, "success", HttpStatus.OK.value()),

    /* 失败状态码 */
    FAIL(0, "failed", HttpStatus.INTERNAL_SERVER_ERROR.value()),

    PARAM_IS_INVALID(10001, "par" +
            "ams_is_invalid", HttpStatus.BAD_REQUEST.value()),

    PERMISSION_NOT_ENOUGH(20001, "Permission_not_enough", HttpStatus.FORBIDDEN.value()),
    AUTHENTICATION_FAILED(20002, "Authentication_failed", HttpStatus.UNAUTHORIZED.value()),
    TOKEN_INVALID(20004, "token_invalid", HttpStatus.UNAUTHORIZED.value()),
    TOKEN_CHECK_FAILED(20005, "token_check_failed", HttpStatus.UNAUTHORIZED.value()),
    ;

    private final int code;
    private final String desc;
    private final int httpStatus;
}
