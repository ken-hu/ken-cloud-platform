package pers.ken.cloud.gateway.oauth.handler;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import pers.ken.cloud.common.model.PlatformResult;
import pers.ken.cloud.common.model.ServiceCode;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * <code>ServiceAuthenticationEntryPoint</code>
 * <desc>
 * 描述：
 * <desc/>
 * <b>Creation Time:</b> 2021/6/10 22:16.
 *
 * @author _Ken.Hu
 */
@Slf4j
@Component
public class ServiceAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {
    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException authenticationException) {
        log.error("Occur AuthenticationException", authenticationException);
        Throwable cause = authenticationException.getCause();
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.getHeaders().set("Access-Control-Allow-Origin", "*");
        response.getHeaders().set("Cache-Control", "no-cache");
        String body = JSON.toJSONString(PlatformResult.custom(ServiceCode.TOKEN_INVALID, "请检查token相关信息"));
        DataBuffer buffer = response.bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }
}
