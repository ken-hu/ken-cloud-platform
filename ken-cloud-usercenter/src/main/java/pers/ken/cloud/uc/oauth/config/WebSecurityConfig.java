package pers.ken.cloud.uc.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pers.ken.cloud.uc.oauth.service.AuthUserDetailsServiceImpl;

/**
 * <code>AuthWebSecurityConfig</code>
 * <desc>
 * 描述：
 * <desc/>
 * <b>Creation Time:</b> 2021/4/10 1:35.
 *
 * @author _Ken.Hu
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthUserDetailsServiceImpl authUserDetailsService;

    @Autowired
    public WebSecurityConfig(AuthUserDetailsServiceImpl authUserDetailsService) {
        this.authUserDetailsService = authUserDetailsService;
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean(name = "passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/swagger-ui/**", "/swagger-resources/**", "/webjars/**", "/v3/**","/v2/**")
                .antMatchers("/actuator/**")
                .antMatchers("/test/**")
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http       //关闭CSRF,使用的是JWT，这里不需要csrf
                .csrf().disable()
                .headers().frameOptions().sameOrigin()
                .and()
                .exceptionHandling()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .anonymous()
                .disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(authUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
