package pers.ken.cloud.uc.oauth;

import com.alibaba.fastjson.JSON;
import org.codehaus.jackson.map.util.BeanUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import pers.ken.cloud.common.utils.BeanMapper;
import pers.ken.cloud.uc.oauth.model.AuthUser;

/**
 * <code>OauthContextHolder</code>
 * <desc>
 * 描述：
 * <desc/>
 * <b>Creation Time:</b> 2021/1/19 18:05.
 *
 * @author Gary.Hu
 */
public class OauthContextHolder {
    /**
     * JwtToken内容解析
     */
    public static OauthUser getOauthUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser)authentication.getPrincipal();
        return BeanMapper.copyProperties(authUser, OauthUser.class);
    }
}
