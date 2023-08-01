package com.example.test.sub1.Common;

import com.example.test.sub1.Dto.LoginUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private final LoginUser loginUser;

    public CustomUserDetails(LoginUser loginUser){
        this.loginUser = loginUser;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
        //일단 권한은 보류
    }

    @Override
    public String getPassword() {
        return loginUser.getUserPw();
    }

    @Override
    public String getUsername() {
        return loginUser.getUserNm();
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
        return true;
    }
}
