package com.example.test.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
@Slf4j
// 토큰 유효성 실패 시, Exception 결과를 정해진 form으로 리턴
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    ObjectMapper objectMapper = new ObjectMapper();

    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

      log.error("======================= token error");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.sendRedirect("/");
        try (OutputStream os = response.getOutputStream()) {
            HashMap fail = new HashMap();
            fail.put("errMsg", "유효하지 않은 토큰입니다");
            fail.put("errCd", "ERROR");
            objectMapper.writeValue(os, fail);
            os.flush();
        }
    }
}