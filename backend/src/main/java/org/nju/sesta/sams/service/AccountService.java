package org.nju.sesta.sams.service;

import org.nju.sesta.sams.dao.MailCaptchaRepository;
import org.nju.sesta.sams.dao.RoleRepository;
import org.nju.sesta.sams.dao.UserRepository;
import org.nju.sesta.sams.entity.MailCaptcha;
import org.nju.sesta.sams.entity.Role;
import org.nju.sesta.sams.entity.User;
import org.nju.sesta.sams.enums.RoleName;
import org.nju.sesta.sams.exception.BasicException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    MailCaptchaRepository mailCaptchaRepo;

    /**
     * 用户注册逻辑
     *
     * @param id       账号
     * @param password 密码
     * @param code     邮件中包含的验证码
     */
    public void register(String id, String password, String code) {
        Optional<User> test = userRepo.findById(id);
        if (test.isPresent())
            //账户已存在
            throw new BasicException(HttpStatus.NOT_ACCEPTABLE, "账户已注册");

        checkCode(id, code);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordHash = passwordEncoder.encode(password);
        String emailAddress = id + "@smail.nju.edu.cn";

        User user = new User();
        user.setId(id);
        user.setPasswordEncrypted(passwordHash);
        user.setEmail(emailAddress);
        user.setGrade(id.substring(0, 2));
        user.setLastLogoutDate(Calendar.getInstance());
        user.setLastPasswordResetDate(Calendar.getInstance());
        //从role表中查找STUDENT(此为默认权限)
        Role role = roleRepo.findByRoleName(RoleName.STUDENT).get(0);
        user.setRoles(Arrays.asList(role));
        userRepo.save(user);

    }

    /**
     * 用户重置密码逻辑
     *
     * @param id          用户名
     * @param newPassword 新密码
     * @param code        邮件中包含的验证码
     */
    public void resetPassword(String id, String newPassword, String code) {
        checkCode(id, code);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userRepo.resetPassword(encoder.encode(newPassword), Calendar.getInstance(), id);
    }

    /**
     * 检查验证码是否正确
     *
     * @param id   用户名
     * @param code 待验证的验证码
     */
    private void checkCode(String id, String code) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, -30);
        List<MailCaptcha> list = mailCaptchaRepo.findByEmailAddress(id + "@smail.nju.edu.cn");
        if (list.isEmpty() || list.get(0).getCode().compareToIgnoreCase(code) != 0)
            throw new BasicException(HttpStatus.FORBIDDEN, "验证码错误");
        else if (list.get(0).getBuiltTime().before(c))
            throw new BasicException(HttpStatus.FORBIDDEN, "验证码过期");
    }

}
