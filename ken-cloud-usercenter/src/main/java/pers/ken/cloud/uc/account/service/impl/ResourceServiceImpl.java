package pers.ken.cloud.uc.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pers.ken.cloud.common.utils.BeanMapper;
import pers.ken.cloud.uc.account.dto.ResourceCreateDTO;
import pers.ken.cloud.uc.account.entity.Resource;
import pers.ken.cloud.uc.account.entity.cons.ResourceType;
import pers.ken.cloud.uc.account.mapper.ResourceMapper;
import pers.ken.cloud.uc.account.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

    @Autowired
    public ResourceServiceImpl(ResourceMapper resourceMapper) {
        this.resourceMapper = resourceMapper;
    }

    @Override
    public void create(ResourceCreateDTO dto) {
        Resource resource = BeanMapper.copyProperties(dto, Resource.class);
        resource.setResourceType(ResourceType.convert(dto.getResourceType()));
        resourceMapper.insert(resource);
    }
}
