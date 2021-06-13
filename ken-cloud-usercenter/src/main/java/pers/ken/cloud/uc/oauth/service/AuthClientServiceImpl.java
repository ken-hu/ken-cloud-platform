package pers.ken.cloud.uc.oauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;
import pers.ken.cloud.uc.account.entity.ClientDetail;
import pers.ken.cloud.uc.account.service.ClientDetailService;
import pers.ken.cloud.uc.oauth.model.AuthClientDetail;

/**
 * <code>AuthClientService</code>
 * <desc>
 * 描述：
 * <desc/>
 * <b>Creation Time:</b> 2021/4/14 22:24.
 *
 * @author _Ken.Hu
 */
@Component
public class AuthClientServiceImpl implements ClientDetailsService {
    private final ClientDetailService clientDetailService;

    @Autowired
    public AuthClientServiceImpl(ClientDetailService clientDetailService) {
        this.clientDetailService = clientDetailService;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        ClientDetail clientDetail = clientDetailService.get(clientId);
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
}
