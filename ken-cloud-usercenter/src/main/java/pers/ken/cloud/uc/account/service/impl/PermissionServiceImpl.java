package pers.ken.cloud.uc.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pers.ken.cloud.uc.account.entity.Permission;
import pers.ken.cloud.uc.account.entity.Resource;
import pers.ken.cloud.uc.account.entity.Role;
import pers.ken.cloud.uc.account.mapper.PermissionMapper;
import pers.ken.cloud.uc.account.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.ken.cloud.uc.account.service.RoleService;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<Permission> list(Long userId) {
        List<Role> roles = roleService.list(userId);
        return listByRoles(roles);
    }

    @Override
    public void bindPermissionResources(Long permissionId, List<Resource> resources) {
        resources.forEach(resource -> permissionMapper.insertPermissionRole(permissionId, resource.getId()));
    }

    @Override
    public List<Permission> listByRoles(List<Role> roles) {
        List<Long> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());
        return permissionMapper.listPermissionByRoles(roleIds);
    }

}
