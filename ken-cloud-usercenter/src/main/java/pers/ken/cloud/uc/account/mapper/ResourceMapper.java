package pers.ken.cloud.uc.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.ken.cloud.uc.account.entity.Resource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pers.ken.cloud.uc.account.entity.Role;

import java.util.List;

/**
 * <p>
 * 资源表 Mapper 接口
 * </p>
 *
 * @author _Ken.Hu
 * @since 2021-04-11
 */
@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {

    List<Resource> selectAllByPermissions(@Param("permissionIds") List<Long> permissionIds);

}
