package pers.ken.cloud.uc.oauth.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import pers.ken.cloud.uc.oauth.service.CustomAuthenticationEntryPoint;

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


    @Autowired
    public ResourceServerConfig(TokenStore tokenStore, CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
        this.tokenStore = tokenStore;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/swagger-ui/**", "/swagger-resources/**", "/webjars/**", "/v3/**","/v2/**").permitAll()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/token/**").permitAll()
                .antMatchers("/oauth/token/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated();
        /*http.csrf().disable().authorizeRequests()
                .anyRequest().permitAll().and().logout().permitAll();*///配置不需要登录验证
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .tokenStore(tokenStore)
                .resourceId(resourceId)
                .authenticationEntryPoint(customAuthenticationEntryPoint);
    }


}
