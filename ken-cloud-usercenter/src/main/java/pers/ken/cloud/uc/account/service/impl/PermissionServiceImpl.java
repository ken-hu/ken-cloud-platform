package pers.ken.cloud.uc.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pers.ken.cloud.uc.account.entity.Permission;
import pers.ken.cloud.uc.account.entity.Resource;
import pers.ken.cloud.uc.account.entity.Role;
import pers.ken.cloud.uc.account.entity.cons.ResourceType;
import pers.ken.cloud.uc.account.mapper.PermissionMapper;
import pers.ken.cloud.uc.account.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.ken.cloud.uc.account.service.RoleService;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author _Ken.Hu
 * @since 2021-04-11
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    private final PermissionMapper permissionMapper;
    private final RoleService roleService;

    @Autowired
    public PermissionServiceImpl(PermissionMapper permissionMapper, RoleService roleService) {
        this.permissionMapper = permissionMapper;
        this.roleService = roleService;
    }

    @Override
    public List<Permission> listByUsername(String username) {
        List<Role> roles = roleService.listByUsername(username);
        return listByRoles(roles);
    }

    @Override
    public void bindResources(List<Resource> resources, Long permissionId) {
        resources.forEach(resource -> permissionMapper.insertPermissionRole(permissionId,resource.getId()));
    }

    @Override
    public List<Permission> listByRoles(List<Role> roles) {
        return permissionMapper.listPermissionByRoles(roles);
    }

}
