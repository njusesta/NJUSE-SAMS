package org.nju.sesta.sams.service;

import org.nju.sesta.sams.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class PasswordResetService {
    @Autowired
    UserRepository repository;

    public void resetPassword(String newPassword, String username) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        repository.resetPassword(encoder.encode(newPassword), Calendar.getInstance(), username);
    }
}
