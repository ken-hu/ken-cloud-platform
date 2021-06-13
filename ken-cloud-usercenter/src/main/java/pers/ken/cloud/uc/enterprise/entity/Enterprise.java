package pers.ken.cloud.uc.enterprise.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 企业信息
 * </p>
 *
 * @author _Ken.Hu
 * @since 2021-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_uc_enterprise")
public class Enterprise extends Model<Enterprise> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableField("id")
    private Long id;

    /**
     * 企业名称
     */
    @TableField("name")
    private String name;


    @Override
    public Serializable pkVal() {
        return null;
    }

}
