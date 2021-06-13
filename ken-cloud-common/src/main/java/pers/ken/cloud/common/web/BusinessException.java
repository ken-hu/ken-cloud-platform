package pers.ken.cloud.common.web;

import lombok.Getter;
import pers.ken.cloud.common.model.ServiceCode;

/**
 * <code>BusinessException</code>
 * <desc>
 * 描述：自定义业务异常
 * <desc/>
 * <b>Creation Time:</b> 2021/4/8 22:32.
 *
 * @author _Ken.Hu
 */
@Getter
public abstract class BusinessException extends RuntimeException{
    private static final long serialVersionUID = -6621600607198714035L;
    private final ServiceCode serviceCode;

    public BusinessException(ServiceCode serviceCode) {
        this.serviceCode = serviceCode;
    }

    public BusinessException(String message, ServiceCode serviceCode) {
        super(message);
        this.serviceCode = serviceCode;
    }

    public BusinessException(String message, Throwable cause, ServiceCode serviceCode) {
        super(message, cause);
        this.serviceCode = serviceCode;
    }

    public BusinessException(Throwable cause, ServiceCode serviceCode) {
        super(cause);
        this.serviceCode = serviceCode;
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ServiceCode serviceCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.serviceCode = serviceCode;
    }
}
