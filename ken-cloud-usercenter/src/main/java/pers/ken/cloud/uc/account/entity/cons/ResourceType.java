package pers.ken.cloud.uc.account.entity.cons;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <code>ResourceType</code>
 * <desc>
 * 描述：
 * <desc/>
 * <b>Creation Time:</b> 2021/4/11 13:40.
 *
 * @author _Ken.Hu
 */
@AllArgsConstructor
@Getter
public enum ResourceType implements IEnum<Integer> {
    /**
     * 资源类型
     */
    APP(1, "文件"),
    API(1, "文件"),
    TABLE(1, "文件"),
    TABLE_COL(1, "文件"),
    MENU(1, "文件"),
    MENU_BUTTON(1, "文件"),
    FILE(1, "文件");

    private final int code;
    private final String desc;

    @Override
    public Integer getValue() {
        return this.getCode();
    }

    public static ResourceType convert(Integer code) {
        for (ResourceType val : values()) {
            if (code.equals(val.getCode())) {
                return val;
            }
        }
        throw new IllegalArgumentException("Can not convert form code:" + code);
    }
}
