package pers.ken.cloud.gateway.oauth;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import pers.ken.cloud.gateway.oauth.config.Oauth2Properties;
import pers.ken.cloud.gateway.oauth.handler.ServiceAccessDeniedHandler;
import pers.ken.cloud.gateway.oauth.handler.ServiceAuthenticationEntryPoint;
import reactor.core.publisher.Mono;

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
@EnableWebFluxSecurity
public class ResourceServerConfig {

    private final ServiceAuthenticationEntryPoint serviceAuthenticationEntryPoint;
    private final ServiceAccessDeniedHandler serviceAccessDeniedHandler;
    private final Oauth2Properties oauth2Properties;

    public ResourceServerConfig(ServiceAuthenticationEntryPoint serviceAuthenticationEntryPoint,
                                ServiceAccessDeniedHandler serviceAccessDeniedHandler,
                                Oauth2Properties oauth2Properties) {
        this.serviceAuthenticationEntryPoint = serviceAuthenticationEntryPoint;
        this.serviceAccessDeniedHandler = serviceAccessDeniedHandler;
        this.oauth2Properties = oauth2Properties;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.csrf().disable()
                // authorize
                .authorizeExchange()
                .pathMatchers(oauth2Properties.getWhitelistUrls()).permitAll()
                .pathMatchers(HttpMethod.OPTIONS, "*").permitAll()
                .and()
                // exception handler
                .exceptionHandling()
                .accessDeniedHandler(serviceAccessDeniedHandler)
                .authenticationEntryPoint(serviceAuthenticationEntryPoint)
                .and()
                // resource server config
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter());
        return http.build();
    }


    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
//        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(AuthConstants.AUTHORITY_PREFIX);
//        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(AuthConstants.AUTHORITY_CLAIM_NAME);

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }
}
