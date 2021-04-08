package pers.ken.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * <code>GatewayApp</code>
 * <desc>
 * 描述：
 * <desc/>
 * <b>Creation Time:</b> 2021/4/8 14:13.
 *
 * @author Gary.Hu
 */
@SpringBootApplication(scanBasePackages = "pers.ken.cloud",exclude = DataSourceAutoConfiguration.class)
public class GatewayApp {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApp.class, args);
    }
}
