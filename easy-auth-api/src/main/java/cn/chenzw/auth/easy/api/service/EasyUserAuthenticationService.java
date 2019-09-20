package cn.chenzw.auth.easy.api.service;

import cn.chenzw.auth.easy.core.constants.EasyAuthenticationConstants;
import cn.chenzw.auth.easy.core.core.EasyUserAuthentication;
import cn.chenzw.toolkit.authentication.support.CaptchaBuilders;
import cn.chenzw.toolkit.http.HttpHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
public class EasyUserAuthenticationService {


    @Autowired
    EasyUserAuthentication easyUserAuthentication;

    public void login() {
        easyUserAuthentication.login();
    }

    /**
     * 获取验证码图片
     *
     * @throws IOException
     */
    public void getCaptchaImage() throws IOException {
        String randomAlphanumeric = CaptchaBuilders.randomAlphanumeric(4);

        // 写入session中
        HttpSession session = HttpHolder.getRequest().getSession();
        session.setAttribute(EasyAuthenticationConstants.LOGIN_CAPTCHA_SESSION, randomAlphanumeric);

        BufferedImage bufferedImage = CaptchaBuilders.createDefault().text(randomAlphanumeric).build();
        ImageIO.write(bufferedImage, "JPEG", HttpHolder.getResponse().getOutputStream());
    }

}
