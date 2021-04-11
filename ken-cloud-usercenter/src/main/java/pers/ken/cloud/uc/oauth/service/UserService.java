package pers.ken.cloud.uc.oauth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;
import pers.ken.cloud.uc.oauth.entity.User;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author _Ken.Hu
 * @since 2021-04-10
 */
public interface UserService extends IService<User> , UserDetailsService {

    User get(String username);
}
