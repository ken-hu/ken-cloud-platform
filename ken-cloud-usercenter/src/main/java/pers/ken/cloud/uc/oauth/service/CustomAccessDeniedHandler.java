package pers.ken.cloud.uc.oauth.service;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import pers.ken.cloud.common.web.PlatformResult;
import pers.ken.cloud.common.web.ServiceCode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <code>CustomAccessDeniedHandler</code>
 * <desc>
 * 描述：自定义拒绝访问
 * <desc/>
 * <b>Creation Time:</b> 2021/4/19 22:45.
 *
 * @author _Ken.Hu
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(JSON.toJSONString(PlatformResult.ok(ServiceCode.PERMISSION_NOT_ENOUGH)));
    }
}
