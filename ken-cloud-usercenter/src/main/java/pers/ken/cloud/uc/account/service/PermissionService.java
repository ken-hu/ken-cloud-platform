package pers.ken.cloud.uc.account.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.ken.cloud.uc.account.entity.Permission;
import pers.ken.cloud.uc.account.entity.Resource;
import pers.ken.cloud.uc.account.entity.Role;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author _Ken.Hu
 * @since 2021-04-11
 */
public interface PermissionService extends IService<Permission> {
    /**
     * 查询用户权限集
     * @param userId 用户id
     * @return 权限集
     */
    List<Permission> list(Long userId);

    /**
     * 绑定权限资源集
     * @param permissionId
     * @param resources
     */
    void bindPermissionResources(Long permissionId,List<Resource> resources);

    /**
     * 查询角色权限集
     * @param roles 角色集
     * @return
     */
    List<Permission> listByRoles(List<Role> roles);

}
