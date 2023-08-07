package com.example.test.Config;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


//현재 필터보다 앞단에 예외 처리를 위한 필터를 하나 더 두고 FilterChain.chain 으로 원래의 JWT 유효성 검사를 하던 필터로 요청을 넘겨주는 방법이 있었다. 필터 구성을 이런식으로 해두면 다음 차례 필터의 로직 수행 중 던져진 예외가 앞서 거쳤던 필터로 넘어가서 처리가 가능하게 되나보다😱
//
//        즉, 원래는 요청 ➡️ JwtAuthenticationFilter 의 형태였다면, 요청 ➡️ JwtExceptionFilter ➡️ JwtAuthenticationFilter로 필터를 구성해서 JwtAuthenticationFilter에서 던진 예외를 JwtExceptionFilter가 처리할 수 있도록 했다.

@Slf4j
@Component
public class JwtExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        try {
            chain.doFilter(req, res); // go to 'JwtAuthenticationFilter'
        } catch (JwtException ex) {
            setErrorResponse(HttpStatus.UNAUTHORIZED, res, ex);
        }
    }

    public void setErrorResponse(HttpStatus status, HttpServletResponse res, Throwable ex) throws IOException {
        res.setStatus(HttpStatus.FORBIDDEN.value());
        res.setContentType("application/json; charset=UTF-8");
        //res.sendError(status.value(),ex.getMessage());
        JwtExceptionResponse jwtExceptionResponse = new JwtExceptionResponse(ex.getMessage(),HttpStatus.UNAUTHORIZED);
        log.error("여기 {}",jwtExceptionResponse.convertToJson());
        res.getWriter().write(jwtExceptionResponse.convertToJson());
    }
}