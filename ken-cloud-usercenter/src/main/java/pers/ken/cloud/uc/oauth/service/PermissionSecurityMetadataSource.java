package pers.ken.cloud.uc.oauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import pers.ken.cloud.uc.account.entity.Permission;
import pers.ken.cloud.uc.account.entity.Resource;
import pers.ken.cloud.uc.account.entity.cons.ResourceType;
import pers.ken.cloud.uc.account.service.ResourceService;
import pers.ken.cloud.uc.oauth.model.AuthUser;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
    private final ResourceService resourceService;
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Autowired
    public PermissionSecurityMetadataSource(ResourceService resourceService) {
        this.resourceService = resourceService;
    }


    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        FilterInvocation filterInvocation = (FilterInvocation) object;
        String requestUri = filterInvocation.getHttpRequest().getRequestURI();
        String username = authUser.getUsername();
        List<Resource> resources = resourceService.listByUsername(username, ResourceType.API);
        List<String> apis = resources.stream().map(Resource::getResourceKey).collect(Collectors.toList());
        return null;
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
