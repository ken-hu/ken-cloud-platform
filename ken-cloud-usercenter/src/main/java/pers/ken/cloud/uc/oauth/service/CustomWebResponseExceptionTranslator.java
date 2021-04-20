package pers.ken.cloud.uc.oauth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * <code>WebResponseExceptionTranslator</code>
 * <desc>
 * 描述：自定义异常返回信息
 * <desc/>
 * <b>Creation Time:</b> 2021/4/20 15:56.
 *
 * @author Gary.Hu
 */
@Component
@Slf4j
public class CustomWebResponseExceptionTranslator implements WebResponseExceptionTranslator<OAuth2Exception> {
    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        log.error("CustomWebResponseExceptionTranslator found a exception", e);
        if (e instanceof OAuth2Exception) {
            return ResponseEntity.ok((OAuth2Exception) e);
        }
        return ResponseEntity.ok(new OAuth2Exception(e.getMessage(),e));
    }
}
