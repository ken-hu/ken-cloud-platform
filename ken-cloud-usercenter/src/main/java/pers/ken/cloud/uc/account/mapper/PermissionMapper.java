package pers.ken.cloud.uc.account.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.ken.cloud.uc.account.entity.Permission;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author _Ken.Hu
 * @since 2021-04-11
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

}
