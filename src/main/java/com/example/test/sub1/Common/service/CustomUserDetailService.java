package com.example.test.sub1.Common.service;

import com.example.test.sub1.Common.CustomUserDetails;
import com.example.test.sub1.Dto.LoginUser;
import com.example.test.sub1.Repository.UserRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService{

    private final UserRepository userRepository;

    public UserDetails loadUserByUserId(String userId){

        log.info("로그인테스트id");

        LoginUser loginUser = userRepository.getReferenceById(userId);

        return new CustomUserDetails(loginUser);

    }

    @Override

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        log.info("로그인테스트유저명확인 : " + userId);

        Optional<LoginUser> result = userRepository.findById(userId);

        LoginUser loginUser = result.orElse(new LoginUser());

        log.info("확인:"+loginUser.toString());
        return new CustomUserDetails(loginUser);

    }
}
