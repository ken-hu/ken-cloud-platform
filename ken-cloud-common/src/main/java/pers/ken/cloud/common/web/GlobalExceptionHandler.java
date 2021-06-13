package pers.ken.cloud.common.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pers.ken.cloud.common.model.PlatformResult;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.Objects;

/**
 * <code>ExceptionHandler</code>
 * <desc>
 * 描述：
 * <desc/>
 * <b>Creation Time:</b> 2021/4/8 17:37.
 *
 * @author _Ken.Hu
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理参数校验异常的信息 httpCode 400
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public PlatformResult<Void> handleConstraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
        log.warn("handleConstraintViolationException, uri:{}, caused by: ", request.getRequestURI(), e);
        String message = e
                .getConstraintViolations()
                .iterator()
                .next()
                .getMessage();
        return PlatformResult.invalid(message);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public PlatformResult<Void> handleHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {
        log.warn("HttpMessageNotReadableException, uri:{}, caused by: ", request.getRequestURI(), e);
        return PlatformResult.invalid();
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public PlatformResult<Void> handleBindException(BindException e, HttpServletRequest request) {
        log.warn("BindException, uri:{}, caused by: ", request.getRequestURI(), e);
        String message = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        return PlatformResult.invalid(message);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public PlatformResult<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.warn("MethodArgumentNotValidException, uri:{}, caused by: ", request.getRequestURI(), e);
        String message = Objects.requireNonNull(e
                .getBindingResult()
                .getFieldError())
                .getDefaultMessage();
        return PlatformResult.invalid(message);
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<PlatformResult<Void>> handleBusinessException(BusinessException e, HttpServletRequest request) {
        log.warn("BusinessException, uri:{}, caused by: ", request.getRequestURI(), e);
        return ResponseEntity
                .status(e.getServiceCode().getHttpStatus())
                .body(PlatformResult.fail(e.getServiceCode()));
    }

    /**
     * 处理运行时异常
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public PlatformResult<Void> handleThrowable(Throwable e, HttpServletRequest request) {
        log.warn("Unknown Throwable, uri:{}, caused by: ", request.getRequestURI(), e);
        return PlatformResult.fail();
    }
}
