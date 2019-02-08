package org.nju.sesta.sams.util.captcha;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

public class Captcha {
    @Autowired
    DefaultKaptcha defaultKaptcha;

    String text;
    BufferedImage image;

    public Captcha() {
        getCode();
    }

    public String getCode() {
        text = defaultKaptcha.createText();
        image = defaultKaptcha.createImage(text);
        return text;
    }

//    public BufferedImage getCaptchaImage(String text){
//        if(image!=null){
//            return image;
//        }else {
//
//        }
//    }

    public void write(OutputStream os) throws IOException {
        ImageIO.write(image, "png", os);
        os.close();
    }

}
