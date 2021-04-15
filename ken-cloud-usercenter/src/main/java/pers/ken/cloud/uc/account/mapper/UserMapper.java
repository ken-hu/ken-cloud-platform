package pers.ken.cloud.uc.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.ken.cloud.uc.account.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author _Ken.Hu
 * @since 2021-04-10
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 插入用户角色关联关系
     * @param userId 用户ID
     * @param roleId 角色ID
     */
    void insertUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);
}
