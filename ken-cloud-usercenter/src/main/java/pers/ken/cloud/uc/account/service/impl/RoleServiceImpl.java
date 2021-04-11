package pers.ken.cloud.uc.account.service.impl;

import pers.ken.cloud.uc.account.entity.Role;
import pers.ken.cloud.uc.account.mapper.RoleMapper;
import pers.ken.cloud.uc.account.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
