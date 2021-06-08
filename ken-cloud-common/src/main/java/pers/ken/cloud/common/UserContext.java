package pers.ken.cloud.common;

/**
 * <code>UserContext</code>
 * <desc>
 * 描述：
 * <desc/>
 * <b>Creation Time:</b> 2021/6/6 12:53.
 *
 * @author _Ken.Hu
 */
public class UserContext {
    private static final ThreadLocal<UserDTO> context = new ThreadLocal<>();


    private UserContext(){

    }

    public static void set(UserDTO user) {
        context.set(user);
    }

    public static UserDTO get() {
        return context.get();
    }

    /**
     * 清除当前线程内引用，防止内存泄漏
     */
    public static void remove() {
        context.remove();
    }
}
