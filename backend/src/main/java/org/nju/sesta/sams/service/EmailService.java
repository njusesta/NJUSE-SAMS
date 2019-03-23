package org.nju.sesta.sams.service;


import com.sun.mail.util.MailSSLSocketFactory;
import org.nju.sesta.sams.dao.MailCaptchaRepository;
import org.nju.sesta.sams.dao.RoleRepository;
import org.nju.sesta.sams.dao.UserRepository;
import org.nju.sesta.sams.entity.MailCaptcha;
import org.nju.sesta.sams.entity.Role;
import org.nju.sesta.sams.entity.User;
import org.nju.sesta.sams.enums.RoleName;
import org.nju.sesta.sams.exception.BadStateException;
import org.nju.sesta.sams.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.*;

@Service
public class EmailService {
    //使用@Value注解导入值
    @Value("${admin.emailAddress}")
    String sendAddress;

    @Value("${admin.emailPassword}")
    String password;

    @Value("${emailContent}")
    String content;

    private static final char[] codeSequence =
            {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    MailCaptchaRepository mailCaptchaRepo;

    public void initAccount(String id) {
        Optional<User> test = userRepo.findById(id);
        if (test.isPresent())
            throw new BadStateException();

        char[] code = new char[8];
        for (int i = 0; i < 8; i++) {
            code[i] = codeSequence[(int) (Math.random() * 35)];//其实也许可以用SecureRandom？
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordHash = passwordEncoder.encode(String.valueOf(code));
        String emailAddress = id + "@smail.nju.edu.cn";

        User user = new User();
        user.setId(id);
        user.setPasswordHash(passwordHash);
        user.setEmail(emailAddress);
        user.setGrade(id.substring(0, 2));
        Role role = new Role();
        role.setRoleName(RoleName.STUDENT);
        user.setRoles(Arrays.asList(role));
        userRepo.save(user);

        String subject = "初始密码";
        try {
            sendSimpleMail(emailAddress, subject, String.format(content, "软院学生活动管理系统", String.valueOf(code)));
        } catch (MessagingException | GeneralSecurityException ex) {
            throw new SystemException();
        }
    }

    @Transactional
    public void sendVerificationCode(String id) {
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

        String prefix = "您(或他人)在申请重置南京大学软件学院学生活动管理系統的账号密码。\n\n" +
                "如果该操作不是由您本人做出，请无视這封邮件。\n\n";
        String body1 =
                "为了重置您的密码，您需要输入该邮件中附帶的验证码以验证您的身份(请註意不要泄露該验证码以免让您的信息面临被泄露和篡改的风险)。\n\n" +
                        "验证码为 %s , 有效时间为30min，过期失效。\n\n";
        String body2 = "然而该邮箱尚未注册， 因此重置密码操作將不能進行。\n\n" +
                "如果您确实拥有该系统的账号並想重置密码，请重試並使用注册账号时所使用的邮箱。\n\n";
        String postfix = "如有疑问，请咨詢管理员。";

        StringBuilder fullContent = new StringBuilder();
        fullContent.append(prefix).append(userRepo.existsById(emailAddress) ? body1 : body2).append(postfix);

        String subject = "南京大学软件学院学生活动管理系统重置密码验证码";
        try {
            sendSimpleMail(subject, emailAddress, String.format(fullContent.toString(), codeStr));
        } catch (MessagingException | GeneralSecurityException ex) {
            throw new SystemException();
        }
    }


    private void sendSimpleMail(String receiveAddress, String subject, String content) throws GeneralSecurityException, MessagingException {
        MimeMessage message = new MimeMessage(init(sendAddress, password));
        message.setFrom(new InternetAddress(sendAddress));// 发件人
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiveAddress));// 收件人
        message.setSubject(subject);// 主题
        message.setText(content);// 内容
        Transport.send(message);
        System.out.println("The message has been sent.");
    }

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

}
