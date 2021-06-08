package pers.ken.cloud.estore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <code>EstoreApp</code>
 * <desc>
 * 描述：
 * <desc/>
 * <b>Creation Time:</b> 2021/6/6 15:13.
 *
 * @author _Ken.Hu
 */
@SpringBootApplication(scanBasePackages = "pers.ken.cloud",exclude = DataSourceAutoConfiguration.class)
@EnableFeignClients(basePackages = "pers.ken.cloud")
public class EstoreApp {
    public static void main(String[] args) {
        SpringApplication.run(EstoreApp.class, args);
    }
}
