package pers.ken.cloud.uc.oauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import pers.ken.cloud.uc.account.entity.Permission;
import pers.ken.cloud.uc.account.service.PermissionService;
import pers.ken.cloud.uc.oauth.model.AuthUser;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * <code>PermissionDecisionVoter</code>
 * <desc>
 * 描述：判断用户是否满足当前资源所要求的权限
 * <desc/>
 * <b>Creation Time:</b> 2021/4/14 23:31.
 *
 * @author _Ken.Hu
 */
@Component
public class PermissionDecisionVoter implements AccessDecisionVoter<Object> {

    private static final String ANONYMOUS_USER = "anonymousUser";
    @Override
    public boolean supports(ConfigAttribute attribute) {
        return Objects.nonNull(attribute.getAttribute());
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    /**
     *
     * @param authentication
     * @param object
     * @param attributes
     * @return
     */
    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        Object principal = authentication.getPrincipal();

        if (ANONYMOUS_USER.equals(principal)) {
            // 当前用户未登录，拒绝访问
            return ACCESS_DENIED;
        }

        if (CollectionUtils.isEmpty(attributes)) {
            return ACCESS_DENIED;
        }
        AuthUser authUser = (AuthUser) principal;
        System.out.println(authUser);
        // todo 判断权限
        return ACCESS_DENIED;
    }
}
