package pers.ken.cloud.uc.auth;

import com.google.common.collect.Maps;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import pers.ken.cloud.common.utils.BeanMapper;
import pers.ken.cloud.uc.auth.model.AuthUser;

import java.util.HashMap;
import java.util.Map;

/**
 * <code>OauthContextHolder</code>
 * <desc>
 * 描述：
 * <desc/>
 * <b>Creation Time:</b> 2021/1/19 18:05.
 *
 * @author _Ken.Hu
 */
public class OauthContextHolder {

    private OauthContextHolder(){}

    /**
     * JwtToken内容解析
     */
    public static OauthUser getOauthUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        return BeanMapper.copyProperties(authUser, OauthUser.class);
    }

    public static Map<String, String> allUserInfo() {
        OauthUser oauthUser = getOauthUser();
        Map<String, String> extInfo = oauthUser.getExtInfo();
        HashMap<String, String> allUserInfo = Maps.newHashMap();
        allUserInfo.putAll(extInfo);
        allUserInfo.put("username", oauthUser.getUsername());
        allUserInfo.put("id", String.valueOf(oauthUser.getId()));
        return allUserInfo;
    }
}
