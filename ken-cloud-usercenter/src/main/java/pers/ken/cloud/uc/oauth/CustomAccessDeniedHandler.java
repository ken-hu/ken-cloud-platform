package pers.ken.cloud.uc.oauth;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import pers.ken.cloud.common.model.PlatformResult;
import pers.ken.cloud.common.model.ServiceCode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

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
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.error("Occur AccessDeniedException", accessDeniedException);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        response.getWriter().write(JSON.toJSONString(PlatformResult.custom(ServiceCode.PERMISSION_NOT_ENOUGH, "请联系管理员获取权限")));
    }
}
