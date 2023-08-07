package com.example.test.Config;

import com.example.test.sub1.Dto.LoginUser;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import java.util.List;

@Slf4j
@Order(0)
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            log.info("헤더값 출력 : " + request.getHeader(HttpHeaders.AUTHORIZATION));
            String token = parseBearerToken(request);
            log.info("토큰체크 :" + request.getRequestURI() + ":" + token);
//            if(token==null && !request.getRequestURI().equals("/loginForm")){
//                response.sendRedirect("/loginForm");
//                filterChain.doFilter(request, response); // "/login" 요청이 들어오면, 다음 필터 호출
//                return; // return으로 이후 현재 필터 진행 막기 (안해주면 아래로 내려가서 계속 필터 진행시킴)
//            }
//            log.info("여기? : ");
            User user = parseUserSpecification(token);
            log.info("유저권한 : " + user.getAuthorities());
            AbstractAuthenticationToken authenticated = UsernamePasswordAuthenticationToken.authenticated(user, token, user.getAuthorities());
            authenticated.setDetails(new WebAuthenticationDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticated);

//        }            catch (Exception e) {
//                log.error("토큰검증 에러" +  e.getMessage());
//                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//                request.setAttribute("exception", e);
//            }
        } catch (IllegalArgumentException e) {
            logger.error("an error occured during getting username from token", e);
            // JwtException (custom exception) 예외 발생시키기
            throw new JwtException("유효하지 않은 토큰");
        } catch (ExpiredJwtException e) {
            logger.warn("the token is expired and not valid anymore", e);
            throw new JwtException("토큰 기한 만료");
        } catch(SignatureException e){
            logger.error("Authentication Failed. Username or Password not valid.");
            throw new JwtException("사용자 인증 실패");
        }

        filterChain.doFilter(request, response);
    }

    private String parseBearerToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
                .filter(token -> token.substring(0, 7).equalsIgnoreCase("Bearer "))
                .map(token -> token.substring(7))
                .orElse(null);
    }

    private User parseUserSpecification(String token) {
        String[] split = Optional.ofNullable(token)
                .filter(subject -> subject.length() >= 10)   //predicate 값이 참이면 해당 필터를 통과시키고 거짓이면 통과 되지 않습니다.
                .map(tokenProvider::validateTokenAndGetSubject)  //mapper 함수를 통해 입력값을 다른 값으로 변환하는 메서드입니다.
                .orElse("anonymous:anonymous")
                .split(":");

        log.info("split0 : " + split[0]);
        log.info("split1 : " + split[1]);
        return new User(split[0], "", List.of(new SimpleGrantedAuthority(split[1])));
    }

    //url 제외
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String[] excludePath = {"/login","/loginProceed", "/css", "/js","/favicon.ico","/vue-front","/img","/error", "/swagger-ui","/api-docs","/signUp","/fonts/","favicon.ico"};
        String path = request.getRequestURI();
        return Arrays.stream(excludePath).anyMatch(path::startsWith);
    }
}