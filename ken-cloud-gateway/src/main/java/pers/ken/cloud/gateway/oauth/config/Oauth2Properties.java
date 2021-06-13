package pers.ken.cloud.gateway.oauth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * <code>Oauth2Properties</code>
 * <desc>
 * 描述：
 * <desc/>
 * <b>Creation Time:</b> 2021/6/10 22:39.
 *
 * @author _Ken.Hu
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "oauth2")
public class Oauth2Properties {
    private String[] whitelistUrls;
}
