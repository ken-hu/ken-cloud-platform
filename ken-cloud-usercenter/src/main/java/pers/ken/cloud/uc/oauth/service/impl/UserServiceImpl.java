package pers.ken.cloud.uc.oauth.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pers.ken.cloud.uc.oauth.entity.User;
import pers.ken.cloud.uc.oauth.mapper.UserMapper;
import pers.ken.cloud.uc.oauth.model.AuthUser;
import pers.ken.cloud.uc.oauth.service.UserService;

import java.util.Collections;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author _Ken.Hu
 * @since 2021-04-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User get(String username) {
        return userMapper.selectOne(
                Wrappers.lambdaQuery(User.class)
                        .eq(User::getUsername, username)
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = get(username);
        if (null == user) {
            throw new UsernameNotFoundException("Cannot found username:" + username);
        }

        return new AuthUser(user.getUsername(), user.getPassword(), true, true, true, true, Collections.EMPTY_SET);
    }
}
