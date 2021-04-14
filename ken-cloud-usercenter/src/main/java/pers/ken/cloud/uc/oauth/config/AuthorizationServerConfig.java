package pers.ken.cloud.uc.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import pers.ken.cloud.uc.oauth.service.AuthTokenEnhancer;

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
    private final UserDetailsService userDetailsService;
    private final ClientDetailsService clientDetailsService;
    private final DataSource dataSource;

    @Autowired
    public AuthorizationServerConfig(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, ClientDetailsService clientDetailsService, DataSource dataSource) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.clientDetailsService = clientDetailsService;
        this.dataSource = dataSource;
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        // jwt使用这个key来签名，验证token的服务也使用这个key来验签
        jwtAccessTokenConverter.setSigningKey("_ken.hu");
        return jwtAccessTokenConverter;
    }

    @Bean
    public TokenStore tokenStore() {
        //基于JwtToken认证
        return new JwtTokenStore(jwtAccessTokenConverter());
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
                //获取token的请求，不进行拦截
                .tokenKeyAccess("permitAll()")
                //检查token的请求，要先通过验证
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    /**
     * client信息
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }

    /**
     * 主配置信息
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        enhancerChain.setTokenEnhancers(Arrays.asList(authTokenEnhancer(), jwtAccessTokenConverter()));

        endpoints
                .accessTokenConverter(jwtAccessTokenConverter())
                .tokenEnhancer(enhancerChain)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }

}
