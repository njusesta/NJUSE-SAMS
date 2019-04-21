package org.nju.sesta.sams.service;

import org.nju.sesta.sams.dao.UserRepository;
import org.nju.sesta.sams.entity.User;
import org.nju.sesta.sams.exception.BasicException;
import org.nju.sesta.sams.factory.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;


@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(username);
        return user.map(JwtUserFactory::create).orElseThrow(
                () -> new UsernameNotFoundException(String.format("No student found with id '%s'.", username)));
    }

    public void logout(String username) {
        User user = userRepository.findByEmail(username);

        if (user == null)
            throw new BasicException(HttpStatus.BAD_REQUEST, "请求失败");

        user.setLastLogoutDate(Calendar.getInstance());
        userRepository.save(user);

    }
}