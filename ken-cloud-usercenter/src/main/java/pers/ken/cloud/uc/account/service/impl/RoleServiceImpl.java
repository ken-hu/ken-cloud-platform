package pers.ken.cloud.uc.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import pers.ken.cloud.uc.account.entity.Role;
import pers.ken.cloud.uc.account.entity.User;
import pers.ken.cloud.uc.account.mapper.RoleMapper;
import pers.ken.cloud.uc.account.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.ken.cloud.uc.account.service.UserService;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author _Ken.Hu
 * @since 2021-04-11
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    private final RoleMapper roleMapper;
    private final UserService userService;

    @Autowired
    public RoleServiceImpl(RoleMapper roleMapper, UserService userService) {
        this.roleMapper = roleMapper;
        this.userService = userService;
    }

    @Override
    public List<Role> listByUsername(String username) {
        User user = userService.get(username);
        Assert.notNull(user,"Can not found user by:"+ username);
        return roleMapper.selectAllByUserId(user.getId());
    }
}
