package pers.ken.cloud.common.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * <code>BeanMapper</code>
 * <desc>
 * 描述：Bean转换类，集成SpringBoot的BeanUtils，做简单的封装加强
 * <desc/>
 * <b>Create Time:</b> 2020/4/22 15:13.
 *
 * @author _Ken.Hu
 */
public class BeanMapper extends BeanUtils {

    public static <T> T copyProperties(Object source, Class<T> clazz) {
        T target = null;
        try {
            target = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("BeanMapper convert fail !! from " + source.getClass().getName() + " to " + clazz.getName(), e);
        }
        copyProperties(source, target);
        return target;
    }

    public static <T> T copyProperties(Object source, Supplier<T> target) {
        T t = target.get();
        copyProperties(source, t);
        return t;
    }

    public static <S, T> List<T> copyListProperties(List<S> sources, Class<T> clazz) {
        List<T> list = new ArrayList<>(sources.size());
        for (S source : sources) {
            T target = null;
            try {
                target = clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException("BeanMapper convert fail !! from " + sources.getClass().getName() + " to " + clazz.getName(),e);
            }
            copyProperties(source, target);
            list.add(target);
        }
        return list;
    }

}
