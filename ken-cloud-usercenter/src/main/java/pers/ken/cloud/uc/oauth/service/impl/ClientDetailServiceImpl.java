package pers.ken.cloud.uc.oauth.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import pers.ken.cloud.uc.oauth.entity.ClientDetail;
import pers.ken.cloud.uc.oauth.mapper.ClientDetailMapper;
import pers.ken.cloud.uc.oauth.model.AuthClientDetail;
import pers.ken.cloud.uc.oauth.service.ClientDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * OauthClient信息表 服务实现类
 * </p>
 *
 * @author _Ken.Hu
 * @since 2021-04-10
 */
@Service
public class ClientDetailServiceImpl extends ServiceImpl<ClientDetailMapper, ClientDetail> implements ClientDetailService {

    private final ClientDetailMapper clientDetailMapper;

    @Autowired
    public ClientDetailServiceImpl(ClientDetailMapper clientDetailMapper) {
        this.clientDetailMapper = clientDetailMapper;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        ClientDetail clientDetail = get(clientId);
        if (null == clientDetail) {
            throw new ClientRegistrationException("Cannot found client:" + clientId);
        }

        return new AuthClientDetail(clientDetail.getClientId(),
                clientDetail.getResourceIds(),
                clientDetail.getScope(),
                clientDetail.getAuthorizedGrantTypes(),
                clientDetail.getAuthorities(),
                clientDetail.getRegisteredRedirectUri());
    }

    @Override
    public ClientDetail get(String clientId) {
        return clientDetailMapper.selectOne(
                Wrappers
                        .lambdaQuery(ClientDetail.class)
                        .eq(ClientDetail::getClientId, clientId)

        );
    }
}
