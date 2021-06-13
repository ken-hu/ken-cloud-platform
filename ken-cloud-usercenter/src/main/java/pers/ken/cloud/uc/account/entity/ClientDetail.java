package pers.ken.cloud.uc.account.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * OauthClient信息表
 * </p>
 *
 * @author _Ken.Hu
 * @since 2021-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_uc_oauth_client_detail")
public class ClientDetail extends Model<ClientDetail> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableField("id")
    private Long id;

    @TableField("client_id")
    private String clientId;

    /**
     * token有效期
     */
    @TableField("access_token_validity_seconds")
    private Integer accessTokenValiditySeconds;

    @TableField("additional_information")
    private String additionalInformation;

    /**
     * 授权信息
     */
    @TableField("authorities")
    private String authorities;

    /**
     * 授权类型
     */
    @TableField("authorized_grant_types")
    private String authorizedGrantTypes;

    @TableField("auto_approve")
    private String autoApprove;

    /**
     * 密码
     */
    @TableField("client_secret")
    private String clientSecret;

    /**
     * refreshToken有效期
     */
    @TableField("refresh_token_validity_seconds")
    private Integer refreshTokenValiditySeconds;

    @TableField("registered_redirect_uri")
    private String registeredRedirectUri;

    /**
     * 资源IDs
     */
    @TableField("resource_ids")
    private String resourceIds;

    /**
     * 生效范围
     */
    @TableField("scope")
    private String scope;

    /**
     * 密码是否必填
     */
    @TableField("secret_required")
    private String secretRequired;

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
        return null;
    }

}
