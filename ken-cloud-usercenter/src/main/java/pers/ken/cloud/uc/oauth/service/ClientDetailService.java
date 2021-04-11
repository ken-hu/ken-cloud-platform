package pers.ken.cloud.uc.oauth.service;

import org.springframework.security.oauth2.provider.ClientDetailsService;
import pers.ken.cloud.uc.oauth.entity.ClientDetail;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * OauthClient信息表 服务类
 * </p>
 *
 * @author _Ken.Hu
 * @since 2021-04-10
 */
public interface ClientDetailService extends IService<ClientDetail>, ClientDetailsService {
    ClientDetail get(String clientId);
}
