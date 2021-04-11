package pers.ken.cloud.uc.oauth.service;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <code>AuthenticationEntryPoint</code>
 * <desc>
 * 描述：
 * <desc/>
 * <b>Creation Time:</b> 2021/4/10 1:42.
 *
 * @author _Ken.Hu
 */
public class AuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

    }
}
