package pers.ken.cloud.uc.account.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.ken.cloud.uc.account.entity.User;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author _Ken.Hu
 * @since 2021-04-10
 */
public interface UserService extends IService<User>  {

    User get(String username);
}
