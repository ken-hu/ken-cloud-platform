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
    List<Permission> listByUsername(String username);

    void bindResources(List<Resource> resources,Long permissionId);

    List<Permission> listByRoles(List<Role> roles);
}
