package org.nju.sesta.sams.service;

import org.nju.sesta.sams.dao.UserRepository;
import org.nju.sesta.sams.entity.User;
import org.nju.sesta.sams.factory.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No student found with email '%s'.", username));
        } else {
            return JwtUserFactory.create(user);
        }
    }
}