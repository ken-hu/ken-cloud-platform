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
    APP(1, "应用"),
    API(2, "API"),
    TABLE(3, "表"),
    TABLE_COL(4, "表字段"),
    MENU(5, "菜单"),
    MENU_BUTTON(6, "菜单按钮"),
    FILE(7, "文件");

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
