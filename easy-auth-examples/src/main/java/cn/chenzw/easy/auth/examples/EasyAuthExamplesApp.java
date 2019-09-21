package cn.chenzw.easy.auth.examples;

import cn.chenzw.auth.easy.api.anotation.EnableEasyUserAuthentication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEasyUserAuthentication
public class EasyAuthExamplesApp {

    public static void main(String[] args) {
        SpringApplication.run(EasyAuthExamplesApp.class, args);
    }
}
