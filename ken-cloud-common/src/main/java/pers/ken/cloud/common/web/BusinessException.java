package pers.ken.cloud.common.web;

import lombok.Getter;
import org.springframework.http.HttpStatus;

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
public class BusinessException extends RuntimeException{
    private int code;
    private String msg;
    private ServiceCode serviceCode;
    private HttpStatus httpStatus;
}
