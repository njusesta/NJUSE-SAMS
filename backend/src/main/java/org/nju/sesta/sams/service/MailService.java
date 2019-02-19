package org.nju.sesta.sams.service;


import com.sun.mail.util.MailSSLSocketFactory;
import org.nju.sesta.sams.dao.RoleRepository;
import org.nju.sesta.sams.dao.UserRepository;
import org.nju.sesta.sams.entity.Role;
import org.nju.sesta.sams.entity.User;
import org.nju.sesta.sams.enums.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Properties;

@Service
public class MailService {
    //使用@Value注解导入值
    @Value("${admin.emailAddress}")
    String sendAddress;

    @Value("${admin.emailPassword}")
    String password;

    @Value("${emailContent}")
    String content;

    @Autowired
    UserRepository userDao;

    @Autowired
    RoleRepository roleDao;

    /**
     * @param id
     * @throws FileNotFoundException
     * @throws IOException              getProperties方法中的异常
     * @throws GeneralSecurityException sendSimpleMail方法异常
     * @throws MessagingException       Message方法调用异常sendSimpleMail方法中
     * @throws Exception                getHost方法中的host不存在异常，这个后面会改的更明确
     */
    public void initAccount(String id) throws GeneralSecurityException, MessagingException, Exception {
        char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        char[] code = new char[8];
        for (int i = 0; i < 8; i++) {
            code[i] = codeSequence[(int) (Math.random() * 35)];//其实也许可以用SecureRandom？
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordHash = passwordEncoder.encode(String.valueOf(code));
        String emailAddress = id+"@smail.nju.edu.cn";

        User user = new User();
        user.setId(id);
        user.setPasswordHash(passwordHash);
        user.setEmail(emailAddress);
        user.setGrade(id.substring(0,2));
        Role role = new Role();
        role.setRoleName(RoleName.STUDENT);
        user.setRoles(Arrays.asList(role));
        userDao.save(user);

        String subject = "初始密码";
        sendSimpleMail(emailAddress, subject, String.format(content, "软院学生活动管理系统", String.valueOf(code)));
    }



    /**
     * @param subject
     * @param receiveAddress
     * @param content
     * @throws GeneralSecurityException init出现异常
     * @throws MessagingException       Message发送出现异常，一般是方法调用问题
     * @throws Exception                host未找到异常
     */
    private void sendSimpleMail(String receiveAddress, String subject, String content) throws GeneralSecurityException, MessagingException, Exception {
        MimeMessage message = new MimeMessage(init(sendAddress, password));
        message.setFrom(new InternetAddress(sendAddress));// 发件人
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiveAddress));// 收件人
        message.setSubject(subject);// 主题
        message.setText(content);// 内容
        Transport.send(message);
        System.out.println("The message has been sent.");
    }

    /**
     * @param sendAddress
     * @param password
     * @return
     * @throws GeneralSecurityException
     * @throws Exception                继续上抛host未找到问题
     */
    private static Session init(final String sendAddress, final String password)
            throws GeneralSecurityException, Exception {
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
     * @param sendAddress
     * @return
     * @throws Exception host未找到
     */
    private static String getHost(String sendAddress) throws Exception {
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
            throw new Exception("Unknown Host.");
        }
    }

}
