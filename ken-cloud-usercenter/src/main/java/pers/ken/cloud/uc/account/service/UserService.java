package pers.ken.cloud.uc.account.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.ken.cloud.uc.account.entity.Role;
import pers.ken.cloud.uc.account.entity.User;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author _Ken.Hu
 * @since 2021-04-10
 */
public interface UserService extends IService<User>  {

    /**
     * 获取用户信息
     * @param username 用户名
     * @return 用户信息
     */
    User get(String username);

    /**
     * 用户绑定角色
     * @param userId 用户Id
     * @param roles 角色Id
     */
    void bindUserRole(Long userId, List<Role> roles);

}
