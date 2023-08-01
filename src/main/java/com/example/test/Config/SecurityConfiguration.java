package com.example.test.Config;


import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.Token;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    private final TokenProvider tokenProvider;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final String[] allowedUrls = {"/", "/loginForm", "/loginProceed", "/js/**", "/vue-front/**", "/img/*", "/error", "/swagger-ui/**", "/api-docs/**", "/signUp", "/fonts/*","favicon.ico"};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("인증필터 들어옴");
        ;

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);    //음?
        http.cors().disable()
                .csrf().disable()
                .httpBasic().disable()
                .authorizeHttpRequests(request -> request
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .requestMatchers(allowedUrls).permitAll()
                        .anyRequest().authenticated()

                )
//                .formLogin(login -> login	// form 방식 로그인 사용
//                        .defaultSuccessUrl("/", true)	// 성공 시 dashboard로
//                        .loginPage("/loginForm")
//                        .loginProcessingUrl("/loginProceed")
//                        .permitAll()	// 대시보드 이동이 막히면 안되므로 얘는 허용
//                )
                .logout(withDefaults())    // 로그아웃은 기본설정으로 (/logout으로 인증해제)
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)    // 세션을 사용하지 않으므로 STATELESS 설정

                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}