package pers.ken.cloud.uc.data;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.IntStream;

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
public class DataPermissionAop {
    private final SpelExpressionParser spelParser = new SpelExpressionParser();

    private final static Map<String, String> TEST_PERMISSIONS = new HashMap<>();

    @Around("@annotation(dataPermission))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint, DataPermission dataPermission) throws Throwable {
        TEST_PERMISSIONS.put("channelId", "1");
        TEST_PERMISSIONS.put("user_id", "1");
        TEST_PERMISSIONS.put("dept", "销售部,采购部");
        // todo 通过 dataPermission 获取相关的入参信息
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        List<String> paramNameList = Arrays.asList(methodSignature.getParameterNames());
        List<Object> paramValueList = Arrays.asList(joinPoint.getArgs());
        // 获取规则元信息
        String[] ruleMetas = dataPermission.ruleMetas();
        Map<String, String> scopeInfo = new HashMap<>(paramNameList.size());
        for (String ruleMeta : ruleMetas) {
            // 通过spel表达式和反射解析入参
            EvaluationContext ctx = new StandardEvaluationContext();
            IntStream.range(0, paramNameList.size())
                    .forEach(i -> ctx.setVariable(paramNameList.get(i), paramValueList.get(i)));
            Expression expression = spelParser.parseExpression(ruleMeta);
            Collection<String> collection = (Collection<String>) Objects.requireNonNull(expression.getValue(ctx, Collection.class));
            scopeInfo.put(ruleMeta, collection.toString());
        }
        // 查询当前用户是否具有这些规则元信息的权限
        log.info("Current User scopeInfo:{}", scopeInfo);

        // 没有则抛出异常信息 全局异常返回

        // 自定义规则元信息把信息解析然后利用mybatisPlugin拼接SQL查询

        return joinPoint.proceed();
    }
}
