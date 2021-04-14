package pers.ken.cloud.uc.account.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.ken.cloud.uc.account.entity.User;
import pers.ken.cloud.uc.account.mapper.UserMapper;
import pers.ken.cloud.uc.account.service.UserService;

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
}
