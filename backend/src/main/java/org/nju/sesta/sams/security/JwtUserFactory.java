package org.nju.sesta.sams.security;

import org.nju.sesta.sams.entity.User;
import org.nju.sesta.sams.enums.RoleName;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JwtUserFactory {

    public static JwtUser create(User user){
        return new JwtUser(
                user.getName(),
                user.getPasswordHash(),
                user.getEmail(),
                mapToGrantedAuthorities(Arrays.asList(RoleName.STUDENT)),
                true,
                user.getLastLogoutDate(),
                user.getLastPasswordResetDate());
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<RoleName> roles) {
        return roles.stream()
                .map(roleName -> new SimpleGrantedAuthority(roleName.name()))
                .collect(Collectors.toList());
    }
}
