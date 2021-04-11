package pers.ken.cloud.uc.account.service.impl;

import pers.ken.cloud.uc.account.entity.Permission;
import pers.ken.cloud.uc.account.mapper.PermissionMapper;
import pers.ken.cloud.uc.account.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author _Ken.Hu
 * @since 2021-04-11
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
