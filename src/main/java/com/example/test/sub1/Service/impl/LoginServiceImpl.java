package com.example.test.sub1.Service.impl;

import com.example.test.Config.TokenProvider;
import com.example.test.sub1.Common.UserType;
import com.example.test.sub1.Dto.LoginUser;
import com.example.test.sub1.Repository.UserRepository;
import com.example.test.sub1.Service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Slf4j
@RequiredArgsConstructor
@Service
public class LoginServiceImpl implements LoginService {
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;    // 추가

    @Transactional(readOnly = true)
    public LoginUser logInProceed(LoginUser request) {
        LoginUser loginUser = userRepository.findById(request.getUserId())
                .filter(it -> it.getUserPw().equals(request.getUserPw()))
                .orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다."));
        log.info("토큰 생성 시작 ");
        String token = tokenProvider.createToken(String.format("%s:%s", loginUser.getUserId(), loginUser.getUserType()));    // 토큰 생성
        log.info("토큰 확인 : " + token);
        String refreshToken = tokenProvider.createRefreshToken(String.format("%s:%s", loginUser.getUserId(), loginUser.getUserType()));    // 토큰 생성
        return new LoginUser(loginUser.getUserId(),loginUser.getUserNm(), UserType.valueOf(loginUser.getUserType()), token, refreshToken);    // 생성자에 토큰 추가
    }

    @Override
    public LoginUser refreshToken(LoginUser request) {
        return null;
    }
}
