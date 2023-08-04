package com.example.test.sub1.Service;

import com.example.test.Config.TokenProvider;
import com.example.test.sub1.Common.UserType;
import com.example.test.sub1.Dto.LoginUser;
import com.example.test.sub1.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface LoginService {
    public LoginUser logInProceed(LoginUser request);

    public LoginUser refreshToken(LoginUser request);

}
