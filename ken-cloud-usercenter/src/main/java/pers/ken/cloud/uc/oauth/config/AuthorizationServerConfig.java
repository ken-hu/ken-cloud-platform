package pers.ken.cloud.uc.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import pers.ken.cloud.uc.oauth.CustomAccessDeniedHandler;
import pers.ken.cloud.uc.oauth.CustomAuthenticationEntryPoint;
import pers.ken.cloud.uc.oauth.service.*;
import pers.ken.cloud.uc.oauth.service.AuthUserDetailsServiceImpl;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * <code>AuthServerConfig</code>
 * <desc>
 * 描述：
 * <desc/>
 * <b>Creation Time:</b> 2021/4/9 23:01.
 *
 * @author _Ken.Hu
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private final AuthenticationManager authenticationManager;
    private final AuthUserDetailsServiceImpl authUserDetailsService;
    private final ClientDetailsService clientDetailsService;
    private final DataSource dataSource;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomWebResponseExceptionTranslator customWebResponseExceptionTranslator;
    private final PasswordEncoder passwordEncoder;
    private final TokenStore tokenStore;
    private final JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    public AuthorizationServerConfig(AuthenticationManager authenticationManager, AuthUserDetailsServiceImpl authUserDetailsService, ClientDetailsService clientDetailsService, DataSource dataSource, CustomAccessDeniedHandler customAccessDeniedHandler, CustomAuthenticationEntryPoint customAuthenticationEntryPoint, CustomWebResponseExceptionTranslator customWebResponseExceptionTranslator, PasswordEncoder passwordEncoder, TokenStore tokenStore, JwtAccessTokenConverter jwtAccessTokenConverter) {
        this.authenticationManager = authenticationManager;
        this.authUserDetailsService = authUserDetailsService;
        this.clientDetailsService = clientDetailsService;
        this.dataSource = dataSource;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
        this.customWebResponseExceptionTranslator = customWebResponseExceptionTranslator;
        this.passwordEncoder = passwordEncoder;
        this.tokenStore = tokenStore;
        this.jwtAccessTokenConverter = jwtAccessTokenConverter;
    }


    /**
     * token信息增强
     */
    @Bean
    public TokenEnhancer authTokenEnhancer() {
        return new AuthTokenEnhancer();
    }

    /**
     * 授权信息保存策略
     */
//    @Bean
//    public ApprovalStore approvalStore(){
//        return new JdbcApprovalStore(dataSource);
//    }

    /**
     * 授权码模式数据来源
     */
//    @Bean
//    public AuthorizationCodeServices authorizationCodeServices(){
//        return new JdbcAuthorizationCodeServices(dataSource);
//    }

    /**
     * token校验策略
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .passwordEncoder(passwordEncoder)
                //获取token的请求，不进行拦截
                .tokenKeyAccess("permitAll()")
                //检查token的请求，要先通过验证
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients()
                .authenticationEntryPoint(customAuthenticationEntryPoint)
                .accessDeniedHandler(customAccessDeniedHandler);
    }

    /**
     * client信息
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.withClientDetails(clientDetailsService);
        clients.inMemory()
                .withClient("usercenter")
                .scopes("all")
                .secret(passwordEncoder.encode("123456"))
                .resourceIds("usercenter","estore")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token","client_credentials")
                .and()
                .withClient("estore")
                .scopes("all")
                .secret(passwordEncoder.encode("123456"))
                .resourceIds("usercenter","estore")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token","client_credentials")
                ;
    }

    /**
     * 主配置信息
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        enhancerChain.setTokenEnhancers(Arrays.asList(authTokenEnhancer(),jwtAccessTokenConverter));
        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(authUserDetailsService)
//                .accessTokenConverter(jwtAccessTokenConverter(authUserDetailsService))
                .tokenStore(tokenStore)
                .accessTokenConverter(jwtAccessTokenConverter)
                .tokenEnhancer(enhancerChain)
                .exceptionTranslator(customWebResponseExceptionTranslator)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }

}
