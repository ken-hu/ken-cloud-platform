package pers.ken.cloud.uc.oauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import pers.ken.cloud.uc.account.entity.Resource;
import pers.ken.cloud.uc.account.entity.enums.ResourceType;
import pers.ken.cloud.uc.account.service.ResourceService;
import pers.ken.cloud.uc.auth.model.AuthUser;

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
    private final ResourceService resourceService;
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    public PermissionDecisionVoter(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return Objects.nonNull(attribute.getAttribute());
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    /**
     * @param authentication 身份信息
     * @param object
     * @param attributes
     * @return
     */
    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        if (ANONYMOUS_USER.equals(authentication.getPrincipal())) {
            // 当前用户未登录，拒绝访问
            return ACCESS_DENIED;
        }

        if (authentication instanceof OAuth2Authentication) {
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
            AuthUser authUser = (AuthUser) oAuth2Authentication.getPrincipal();
            if (CollectionUtils.isEmpty(attributes)) {
                return ACCESS_DENIED;
            }
            // todo restful api need http method
            ConfigAttribute uriConfigAttribute = attributes.iterator().next();
            String uri = uriConfigAttribute.getAttribute();

            // 分割method和uri
            List<Resource> resources = resourceService.list(authUser.getId(), ResourceType.API);
            for (Resource resource : resources) {
                boolean match = antPathMatcher.match(resource.getResourceKey(), uri);
                if (match) {
                    return ACCESS_GRANTED;
                }
            }
        }

        return ACCESS_DENIED;
    }
}
