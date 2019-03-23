package org.nju.sesta.sams.service;

import org.nju.sesta.sams.dao.MailCaptchaRepository;
import org.nju.sesta.sams.dao.UserRepository;
import org.nju.sesta.sams.entity.MailCaptcha;
import org.nju.sesta.sams.exception.BadStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class PasswordService {
    @Autowired
    UserRepository userRepo;

    @Autowired
    MailCaptchaRepository mailCaptchaRepo;

    public void resetPassword(String newPassword, String code, String id) {
        List<MailCaptcha> list = mailCaptchaRepo.findByEmailAddress(id + "@smail.nju.edu.cn");
        if (list.isEmpty() || !list.get(0).getCode().equals(code))
            throw new BadStateException();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userRepo.resetPassword(encoder.encode(newPassword), Calendar.getInstance(), id);
    }


}
