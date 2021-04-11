package pers.ken.cloud.uc.oauth.model;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

/**
 * <code>AuthClientDetails</code>
 * <desc>
 * 描述：
 * <desc/>
 * <b>Creation Time:</b> 2021/4/10 14:38.
 *
 * @author _Ken.Hu
 */
public class AuthClientDetail extends BaseClientDetails {
    public AuthClientDetail() {
    }

    public AuthClientDetail(ClientDetails prototype) {
        super(prototype);
    }

    public AuthClientDetail(String clientId, String resourceIds, String scopes, String grantTypes, String authorities) {
        super(clientId, resourceIds, scopes, grantTypes, authorities);
    }

    public AuthClientDetail(String clientId, String resourceIds, String scopes, String grantTypes, String authorities, String redirectUris) {
        super(clientId, resourceIds, scopes, grantTypes, authorities, redirectUris);
    }
}
