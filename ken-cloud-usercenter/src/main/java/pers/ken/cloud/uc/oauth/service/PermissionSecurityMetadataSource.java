package pers.ken.cloud.uc.oauth.service;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * <code>PermissionSecurityMetadataSource</code>
 * <desc>
 * 描述：动态获取ConfigAttribute(权限资源)
 * <desc/>
 * <b>Creation Time:</b> 2021/4/14 23:37.
 *
 * @author _Ken.Hu
 */
@Component
public class PermissionSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation filterInvocation = (FilterInvocation) object;
        // 获取URI 供PermissionDecisionVoter判断是否允许访问
        HttpServletRequest httpRequest = filterInvocation.getHttpRequest();
        String requestUri = httpRequest.getRequestURI();
        String method = httpRequest.getMethod();
        return SecurityConfig.createList(requestUri);
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
