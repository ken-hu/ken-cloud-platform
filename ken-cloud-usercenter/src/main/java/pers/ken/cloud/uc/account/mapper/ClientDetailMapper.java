package pers.ken.cloud.uc.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import pers.ken.cloud.uc.account.entity.ClientDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * OauthClient信息表 Mapper 接口
 * </p>
 *
 * @author _Ken.Hu
 * @since 2021-04-10
 */
@Mapper
public interface ClientDetailMapper extends BaseMapper<ClientDetail> {

}
