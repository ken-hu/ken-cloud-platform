package pers.ken.cloud.uc.account.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.ken.cloud.uc.account.entity.Permission;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author _Ken.Hu
 * @since 2021-04-11
 */
public interface PermissionService extends IService<Permission> {
    List<Permission> getByUsername(String username);
}
