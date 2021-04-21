package pers.ken.cloud.uc.account.service;

import pers.ken.cloud.uc.account.dto.ResourceCreateDTO;
import pers.ken.cloud.uc.account.dto.ResourceUpdateDTO;
import pers.ken.cloud.uc.account.entity.Permission;
import pers.ken.cloud.uc.account.entity.Resource;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.ken.cloud.uc.account.entity.enums.ResourceType;

import java.util.List;

/**
 * <p>
 * 资源表 服务类
 * </p>
 *
 * @author _Ken.Hu
 * @since 2021-04-11
 */
public interface ResourceService extends IService<Resource> {
    /**
     * 创建资源
     * @param dto 资源信息
     */
    void create(ResourceCreateDTO dto);

    /**
     * 删除资源
     * @param id
     */
    void delete(Integer id);

    /**
     * 资源信息修改
     * @param dto 修改信息
     */
    void edit(ResourceUpdateDTO dto);

    /**
     * 查询权限资源信息
     * @param permissions
     * @return
     */
    List<Resource> listResources(List<Permission> permissions);

    /**
     * 查询用户的资源信息
     * @param userId
     * @return
     */
    List<Resource> list(Long userId);

    /**
     * 查询指定资源类型的资源信息
     * @param userId
     * @param resourceType
     * @return
     */
    List<Resource> list(Long userId, ResourceType resourceType);

}
