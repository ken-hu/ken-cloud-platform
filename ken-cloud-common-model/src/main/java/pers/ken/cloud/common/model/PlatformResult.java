package pers.ken.cloud.common.model;

import lombok.*;

/**
 * <code>PlatformResult</code>
 * <desc>
 * 描述：
 * <desc/>
 * <b>Creation Time:</b> 2021/4/8 21:48.
 *
 * @author _Ken.Hu
 */
@Data
@NoArgsConstructor
public class PlatformResult<T> {
    private int code;
    private String msg;
    private T data;

    private void convertServiceCode(ServiceCode serviceCode) {
        this.code = serviceCode.getCode();
        this.msg = serviceCode.getDesc();
        this.data = null;
    }

    private void convertServiceCode(ServiceCode serviceCode, T data) {
        convertServiceCode(serviceCode);
        this.data = data;
    }

    public static <T> PlatformResult<T> custom(ServiceCode serviceCode,T data) {
        PlatformResult<T> result = new PlatformResult<>();
        result.convertServiceCode(serviceCode,data);
        return result;
    }

    public static <T> PlatformResult<T> ok() {
        PlatformResult<T> result = new PlatformResult<>();
        result.convertServiceCode(ServiceCode.SUCCESS);
        return result;
    }

    public static <T> PlatformResult<T> ok(T data) {
        PlatformResult<T> result = new PlatformResult<>();
        result.convertServiceCode(ServiceCode.SUCCESS, data);
        return result;
    }

    public static <T> PlatformResult<T> fail() {
        PlatformResult<T> result = new PlatformResult<>();
        result.convertServiceCode(ServiceCode.FAIL);
        return result;
    }

    public static <T> PlatformResult<T> fail(ServiceCode serviceCode) {
        PlatformResult<T> result = new PlatformResult<>();
        result.convertServiceCode(serviceCode);
        return result;
    }

    public static <T> PlatformResult<T> fail(T data) {
        PlatformResult<T> result = new PlatformResult<>();
        result.convertServiceCode(ServiceCode.FAIL, data);
        return result;
    }

    public static <T> PlatformResult<T> invalid() {
        PlatformResult<T> result = new PlatformResult<>();
        result.convertServiceCode(ServiceCode.PARAM_IS_INVALID);
        return result;
    }

    public static <T> PlatformResult<T> invalid(String msg) {
        PlatformResult<T> result = new PlatformResult<>();
        result.setCode(ServiceCode.PARAM_IS_INVALID.getCode());
        result.setMsg(msg);
        return result;
    }
}
