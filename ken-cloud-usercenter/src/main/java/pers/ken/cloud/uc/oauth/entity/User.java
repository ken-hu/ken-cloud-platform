package pers.ken.cloud.uc.oauth.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author _Ken.Hu
 * @since 2021-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_uc_oauth_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;

    /**
     * 账号
     */
    @TableField("username")
    private String username;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 电子邮件
     */
    @TableField("email")
    private String email;

    /**
     * 电话号码
     */
    @TableField("mobile")
    private String mobile;

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
