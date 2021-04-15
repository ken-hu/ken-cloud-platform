package pers.ken.cloud.uc.account.service;

import pers.ken.cloud.uc.account.dto.ResourceCreateDTO;
import pers.ken.cloud.uc.account.dto.ResourceUpdateDTO;
import pers.ken.cloud.uc.account.entity.Resource;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 资源表 服务类
 * </p>
 *
 * @author _Ken.Hu
 * @since 2021-04-11
 */
public interface ResourceService extends IService<Resource> {
    void create(ResourceCreateDTO dto);

    void delete(Integer id);

    void edit(ResourceUpdateDTO dto);
}
