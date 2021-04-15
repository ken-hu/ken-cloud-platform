package pers.ken.cloud.uc.account.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.ken.cloud.uc.account.entity.Role;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author _Ken.Hu
 * @since 2021-04-11
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 插入角色权限
     * @param roleId 角色id
     * @param permissionId 权限id
     */
    void insertRolePermission(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

    /**
     * 通过用户ID查询相应角色
     * @param userId 用户ID
     * @return 角色集合
     */
    List<Role> selectAllByUserId(@Param("userId") Long userId);
}
