package pers.ken.cloud.uc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * <code>UcApp</code>
 * <desc>
 * 描述：
 * <desc/>
 * <b>Creation Time:</b> 2021/4/8 17:32.
 *
 * @author _Ken.Hu
 */
@SpringBootApplication
@ComponentScan("pers.ken.cloud")
@MapperScan("pers.ken.cloud.uc.*.mapper")
public class UcApp {
    public static void main(String[] args) {
        SpringApplication.run(UcApp.class, args);
    }
}
