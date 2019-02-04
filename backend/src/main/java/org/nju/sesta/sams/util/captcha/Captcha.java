package org.nju.sesta.sams.util.captcha;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

public class Captcha {
    private int width;
    private int height;
    private String code;
    private BufferedImage buffImg = null;

    public Captcha() {
    }

    public Captcha(int width, int height, String code) {
        this.width = width;
        this.height = height;
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void write(OutputStream sos) throws IOException {
        ImageIO.write(buffImg, "png", sos);
        sos.close();
    }

}
