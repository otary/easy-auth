package cn.chenzw.auth.easy.api;

import cn.chenzw.auth.easy.core.constants.EasyAuthenticationConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = EasyAuthenticationConstants.BASE_PACKAGE)
public class EasyAuthApiApp {

    public static void main(String[] args) {
        SpringApplication.run(EasyAuthApiApp.class, args);
    }
}
