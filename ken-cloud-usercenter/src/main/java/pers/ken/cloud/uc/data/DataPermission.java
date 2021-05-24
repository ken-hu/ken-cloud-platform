package pers.ken.cloud.uc.data;

import java.lang.annotation.*;

/**
 * <code>DataPermission</code>
 * <desc>
 * 描述： 数据权限注解，方法级别
 * <desc/>
 * <b>Creation Time:</b> 2021/4/19 17:29.
 *
 * @author _Ken.Hu
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataPermission {
    String name() default "";

    String desc() default "data permission default desc";

    String[] ruleMetas() default {};
}
