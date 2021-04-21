package pers.ken.cloud.uc.account.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.ken.cloud.uc.account.entity.Permission;

import java.util.List;

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
    /**
     * 插入权限角色关联表
     * @param permissionId 权限ID
     * @param resourceId 资源ID
     */
    void insertPermissionRole(@Param("permissionId") Long permissionId, @Param("resourceId") Long resourceId);

    /**
     * 通过角色寻找权限集合
     * @param roleIds 角色集合
     * @return
     */
    List<Permission> listPermissionByRoles(@Param("roleIds") List<Long> roleIds);
}
