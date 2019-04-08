package org.nju.sesta.sams.service;


import com.sun.mail.util.MailSSLSocketFactory;
import org.nju.sesta.sams.dao.MailCaptchaRepository;
import org.nju.sesta.sams.dao.RoleRepository;
import org.nju.sesta.sams.dao.UserRepository;
import org.nju.sesta.sams.entity.MailCaptcha;
import org.nju.sesta.sams.exception.BasicException;
import org.nju.sesta.sams.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

@Service
public class EmailService {
    //使用@Value注解导入值
    @Value("${admin.emailAddress}")
    String sendAddress;

    @Value("${admin.emailPassword}")
    String password;

    private static final char[] codeSequence =
            {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    MailCaptchaRepository mailCaptchaRepo;


    /**
     * 发送验证码邮件的逻辑
     *
     * @param id   用户账号
     * @param type 邮件种类
     */
    @Transactional
    public void sendVerificationCode(String id, String type) {
        if (id == null || !type.equals("0") && !type.equals("1"))
            throw new BasicException(HttpStatus.BAD_REQUEST, "bad request");

        char[] code = new char[6];
        for (int i = 0; i < 6; i++) {
            code[i] = codeSequence[(int) (Math.random() * 35)];
        }
        String codeStr = String.valueOf(code);

        String emailAddress = id + "@smail.nju.edu.cn";
        MailCaptcha mailCaptcha;
        List<MailCaptcha> res = mailCaptchaRepo.findByEmailAddress(emailAddress);
        mailCaptcha = (res == null || res.size() == 0) ? new MailCaptcha() : res.get(0);//设定每个用户同时最多只有一个验证码
        mailCaptcha.setEmailAddress(emailAddress);
        mailCaptcha.setCode(codeStr);
        mailCaptcha.setBuiltTime(Calendar.getInstance());
        mailCaptchaRepo.save(mailCaptcha);

        String subject = "南京大学软件学院学生活动管理系统重置密码验证码";

        String content = "";
        switch (type) {
            case "0":
                content = constructInitContent(id);
                break;
            case "1":
                content = constructResetContent(id);
                break;
        }

        try {
            sendSimpleMail(emailAddress, subject, String.format(content, codeStr));
        } catch (MessagingException | GeneralSecurityException ex) {
            ex.printStackTrace();
            throw new SystemException();
        }
    }


    /**
     * 发送简单邮件(不带附件)
     *
     * @param receiveAddress 接收地址
     * @param subject        主题
     * @param content        内容
     * @throws GeneralSecurityException
     * @throws MessagingException
     */
    private void sendSimpleMail(String receiveAddress, String subject, String content)
            throws GeneralSecurityException, MessagingException {
        MimeMessage message = new MimeMessage(init(sendAddress, password));
        message.setFrom(new InternetAddress(sendAddress));// 发件人
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiveAddress));// 收件人
        message.setSubject(subject);// 主题
        message.setText(content);// 内容
        Transport.send(message);
        System.out.println("The message has been sent.");
    }

    /**
     * 获取发送邮件需要的session
     *
     * @param sendAddress 发送邮箱
     * @param password    密码，或者授权码
     * @return 构件好的session对象
     * @throws GeneralSecurityException
     */
    private static Session init(final String sendAddress, final String password)
            throws GeneralSecurityException {
        String host = getHost(sendAddress);// 邮件服务器
        Properties properties = System.getProperties();// 系统设置
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        MailSSLSocketFactory sf = new MailSSLSocketFactory();// SSL
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);
        Session session = Session.getDefaultInstance(properties, new Authenticator() {// 授权
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sendAddress, password);
            }
        });
        return session;
    }

    /**
     * 根据邮箱后缀解析host服务器
     *
     * @param sendAddress 发送邮箱
     * @return 对应的host服务器地址
     */
    private static String getHost(String sendAddress) {
        if (sendAddress.endsWith("@smail.nju.edu.cn")) {
            return "smtp.exmail.qq.com";
        } else if (sendAddress.endsWith("@qq.com")) {
            return "smtp.qq.com";
        } else if (sendAddress.endsWith("@126.com")) {
            return "smtp.126.com";
        } else if (sendAddress.endsWith("@163.com")) {
            return "smtp.163.com";
        } else if (sendAddress.endsWith("@sina.com")) {
            return "smtp.sina.com.cn";
        } else if (sendAddress.endsWith("@sohu.com")) {
            return "smtp.sohu.com.cn";
        } else if (sendAddress.endsWith("@139.com")) {
            return "smtp.139.com";
        } else {
            throw new RuntimeException("Unknown Host.");
        }
    }

    /**
     * 构造重置密码邮件的内容
     *
     * @param id 账号
     * @return 包含内容的字符串
     */
    private String constructResetContent(String id) {
        String prefix = "您(或他人)在申请重置南京大学软件学院学生活动管理系统的账号密码。\n\n" +
                "如果该操作不是由您本人做出，请无视这封邮件。\n\n";
        String body1 =
                "为了重置您的密码，您需要输入该邮件中附带的验证码以验证您的身份(请注意不要泄露该验证码以免让您的信息面临被泄露和篡改的风险)。\n\n" +
                        "验证码为 %s , 有效时间为30min，过期失效。\n\n";
        String body2 = "然而该邮箱尚未注册， 因此重置密码操作将不能进行。\n\n" +
                "如果您确实拥有该系统的账号并想重置密码，请重试并使用注册账号时所使用的邮箱。\n\n";
        String postfix = "如有疑问，请咨询管理员。";


        StringBuilder fullContent = new StringBuilder();
        fullContent.append(prefix).append(userRepo.existsById(id) ? body1 : body2).append(postfix);
        return fullContent.toString();
    }

    /**
     * 构造注册邮件的内容
     *
     * @param id 账号
     * @return 包含内容的字符串
     */
    private String constructInitContent(String id) {
        String prefix = "您(或他人)在申请注册南京大学软件学院学生活动管理系统的账号。\n\n" +
                "如果该操作不是由您本人做出，请无视这封邮件。\n\n";
        String body =
                "在注册时，您需要输入该邮件中附带的验证码以验证您的身份(请注意不要泄露该验证码以免让您的信息面临被泄露和篡改的风险)。\n\n" +
                        "验证码为 %s , 有效时间为30min，过期失效。\n\n";
        String postfix = "如有疑问，请咨询管理员。";
        StringBuilder fullContent = new StringBuilder();
        fullContent.append(prefix).append(body).append(postfix);
        return fullContent.toString();
    }

}
