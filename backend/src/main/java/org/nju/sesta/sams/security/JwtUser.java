package org.nju.sesta.sams.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Calendar;
import java.util.Collection;

//Spring Security通过UserDetail的实现类来认证用户
//参考blog: https://www.jianshu.com/p/a65f883de0c1
public class JwtUser implements UserDetails {

    private String username;
    private String password;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean enabled;
    private Calendar lastLogOutDate;
    private Calendar lastPasswordResetDate;

    public JwtUser(
            String username,
            String password,
            String email,
            Collection<? extends GrantedAuthority> authorities,
            boolean enabled,
            Calendar lastLogOutDate,
            Calendar lastPasswordResetDate) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
        this.enabled = enabled;
        this.lastLogOutDate = lastLogOutDate;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public String getEmail() {
        return email;
    }

    public Calendar getLastLogOutDate() {
        return lastLogOutDate;
    }

    public Calendar getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }
}