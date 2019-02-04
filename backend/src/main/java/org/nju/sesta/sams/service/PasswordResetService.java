package org.nju.sesta.sams.service;

import org.nju.sesta.sams.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;

public class PasswordResetService {
    @Autowired
    UserRepository repository;

    public void resetPassword(String username, String newPassword){
        repository.resetPassword(newPassword, Calendar.getInstance(),username);
    }
}
