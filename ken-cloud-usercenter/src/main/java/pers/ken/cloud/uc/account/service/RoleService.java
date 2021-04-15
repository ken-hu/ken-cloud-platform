package pers.ken.cloud.uc.account.service;

import pers.ken.cloud.uc.account.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author _Ken.Hu
 * @since 2021-04-11
 */
public interface RoleService extends IService<Role> {

    List<Role> listByUsername(String username);
}
