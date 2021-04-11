package pers.ken.cloud.uc.account.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author _Ken.Hu
 * @since 2021-04-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_uc_permission")
public class Permission extends Model<Permission> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 名字
     */
    @TableField("name")
    private String name;

    /**
     * 描述
     */
    @TableField("desc")
    private String desc;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 创建人ID
     */
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private Integer createUser;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 更新人ID
     */
    @TableField(value = "update_user", fill = FieldFill.INSERT_UPDATE)
    private Integer updateUser;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
