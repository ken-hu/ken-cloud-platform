package pers.ken.cloud.uc.account.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.ken.cloud.uc.account.entity.enums.ResourceType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 资源表
 * </p>
 *
 * @author _Ken.Hu
 * @since 2021-04-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_uc_resource")
public class Resource extends Model<Resource> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 描述
     */
    @TableField("`desc`")
    private String desc;

    /**
     * 资源类型
     */
    @TableField("resource_type")
    private ResourceType resourceType;

    /**
     * 资源信息
     */
    @TableField("resource_key")
    private String resourceKey;

    /**
     * 拓展信息
     */
    @TableField("ext_info")
    private String extInfo;

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
    public Serializable pkVal() {
        return this.id;
    }

}
