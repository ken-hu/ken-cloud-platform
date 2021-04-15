package pers.ken.cloud.uc.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import pers.ken.cloud.uc.account.service.PermissionService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Collections;

/**
 * <code>PermissionFilter</code>
 * <desc>
 * 描述：
 * <desc/>
 * <b>Creation Time:</b> 2021/4/14 22:33.
 *
 * @author _Ken.Hu
 */
@Component
public class PermissionFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private final PermissionService permissionService;

    @Autowired
    public PermissionFilterInvocationSecurityMetadataSource(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        final HttpServletRequest request = ((FilterInvocation) object).getRequest();
        String requestUrl = request.getRequestURL().toString();
        PathMatcher pathMatcher = new AntPathMatcher();
        return Collections.emptyList();
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
