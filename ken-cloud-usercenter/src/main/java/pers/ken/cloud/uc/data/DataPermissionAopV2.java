package pers.ken.cloud.uc.data;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

/**
 * <code>DataPermissionAop</code>
 * <desc>
 * 描述：
 * <desc/>
 * <b>Creation Time:</b> 2021/4/19 17:34.
 *
 * @author _Ken.Hu
 */
@Aspect
@Component
@Slf4j
@EnableAspectJAutoProxy
public class DataPermissionAopV2 {
    private final SpelExpressionParser spelParser = new SpelExpressionParser();

    @Around("@annotation(dataPermission))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint,DataPermission dataPermission) throws Throwable {
        // todo 通过 dataPermission 获取相关的入参信息
        // 查询当前用户是否具有这些规则元信息的权限
        // 没有则抛出异常信息 全局异常返回
        // 有权限则把信息解析然后利用mybatisPlugin拼接SQL查询
        return joinPoint.proceed();
    }
}
