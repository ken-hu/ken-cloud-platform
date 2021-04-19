package pers.ken.cloud.uc.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pers.ken.cloud.common.utils.BeanMapper;
import pers.ken.cloud.uc.account.dto.ResourceCreateDTO;
import pers.ken.cloud.uc.account.dto.ResourceUpdateDTO;
import pers.ken.cloud.uc.account.entity.Permission;
import pers.ken.cloud.uc.account.entity.Resource;
import pers.ken.cloud.uc.account.entity.cons.ResourceType;
import pers.ken.cloud.uc.account.mapper.ResourceMapper;
import pers.ken.cloud.uc.account.service.PermissionService;
import pers.ken.cloud.uc.account.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author _Ken.Hu
 * @since 2021-04-11
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    private final ResourceMapper resourceMapper;
    private final PermissionService permissionService;

    @Autowired
    public ResourceServiceImpl(ResourceMapper resourceMapper, PermissionService permissionService) {
        this.resourceMapper = resourceMapper;
        this.permissionService = permissionService;
    }

    @Override
    public void create(ResourceCreateDTO dto) {
        Resource resource = BeanMapper.copyProperties(dto, Resource.class);
        resource.setResourceType(ResourceType.convert(dto.getResourceType()));
        resourceMapper.insert(resource);
    }

    @Override
    public void delete(Integer id) {
        resourceMapper.deleteById(id);
    }

    @Override
    public void edit(ResourceUpdateDTO dto) {
        ResourceUpdateDTO resourceUpdateDTO = new ResourceUpdateDTO();
        Resource resource = BeanMapper.copyProperties(resourceUpdateDTO, Resource.class);
        resourceMapper.updateById(resource);
    }

    @Override
    public List<Resource> listResources(List<Permission> permissions) {
        return resourceMapper.selectAllByPermissions(permissions.stream().map(Permission::getId).collect(Collectors.toList()));
    }



    @Override
    public List<Resource> listByUsername(String username) {
        List<Permission> permissions = permissionService.listByUsername(username);
        return listResources(permissions);
    }

    @Override
    public List<Resource> listByUsername(String username, ResourceType resourceType) {
        List<Resource> resources = listByUsername(username);
        return resources.stream().filter(resource -> resource.getResourceType().equals(resourceType)).collect(Collectors.toList());
    }


}
