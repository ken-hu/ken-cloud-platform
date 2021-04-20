package pers.ken.cloud.uc.oauth.service;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import pers.ken.cloud.common.web.PlatformResult;
import pers.ken.cloud.common.web.ServiceCode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * <code>CustomAuthenticationEntryPoint</code>
 * <desc>
 * 描述：自定义减权失败返回信息
 * <desc/>
 * <b>Creation Time:</b> 2021/4/19 22:57.
 *
 * @author _Ken.Hu
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Throwable cause = authException.getCause();
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        PrintWriter writer = response.getWriter();
        if (cause instanceof InvalidTokenException) {
            writer.write(JSON.toJSONString(PlatformResult.custom(ServiceCode.TOKEN_INVALID,"请检查token相关信息")));
        } else {
            writer.write(JSON.toJSONString(PlatformResult.custom(ServiceCode.AUTHENTICATION_FAIL,"请检查token是否有权限")));
        }
    }
}
