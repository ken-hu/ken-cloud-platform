package pers.ken.cloud.uc.oauth.config;


import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import pers.ken.cloud.uc.oauth.service.*;

/**
 * <code>ResourceServerConfig</code>
 * <desc>
 * 描述：
 * <desc/>
 * <b>Creation Time:</b> 2020/3/3 11:46.
 *
 * @author _Ken.Hu
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value("${spring.application.name}")
    private String resourceId;

    private final TokenStore tokenStore;

    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    private final PermissionDecisionVoter permissionDecisionVoter;
    private final PermissionSecurityMetadataSource permissionSecurityMetadataSource;
    private final UserDetailsService userDetailsService;


    @Autowired
    public ResourceServerConfig(TokenStore tokenStore, CustomAuthenticationEntryPoint customAuthenticationEntryPoint, CustomAccessDeniedHandler customAccessDeniedHandler, PermissionDecisionVoter permissionDecisionVoter, PermissionSecurityMetadataSource permissionSecurityMetadataSource, UserDetailsService userDetailsService) {
        this.tokenStore = tokenStore;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
        this.permissionDecisionVoter = permissionDecisionVoter;
        this.permissionSecurityMetadataSource = permissionSecurityMetadataSource;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/swagger-ui/**", "/swagger-resources/**", "/webjars/**", "/v3/**", "/v2/**").permitAll()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/token/**").permitAll()
                .antMatchers("/oauth/token/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .and()
                .userDetailsService(userDetailsService)
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(permissionSecurityMetadataSource);
                        object.setAccessDecisionManager(accessDecisionManager());
                        return object;
                    }
                });

    }

    private AccessDecisionManager accessDecisionManager() {

        WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
        webExpressionVoter.setExpressionHandler(new OAuth2WebSecurityExpressionHandler());
        // 授权逻辑自定义配置
        return new AffirmativeBased(Lists.newArrayList(permissionDecisionVoter, new RoleVoter(),
                new AuthenticatedVoter(), webExpressionVoter));
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .tokenStore(tokenStore)
                .resourceId(resourceId)
                .authenticationEntryPoint(customAuthenticationEntryPoint)
        .accessDeniedHandler(customAccessDeniedHandler);
    }


}
