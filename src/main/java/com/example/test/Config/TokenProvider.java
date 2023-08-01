package com.example.test.Config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

@Slf4j
@PropertySource("classpath:jwt.yml")
@Component
public class TokenProvider {
    private final String secretKey;
    private final long expirationHours;
    private final String issuer;

    public TokenProvider(
            @Value("${secret-key}") String secretKey,
            @Value("${expiration-hours}") long expirationHours,
            @Value("${issuer}") String issuer
    ) {
        this.secretKey = secretKey;
        this.expirationHours = expirationHours;
        this.issuer = issuer;

    }

    public String createToken(String userSpecification) {
        log.info("secret key" + secretKey);
        byte KeyByte[] = Base64.getDecoder().decode(secretKey);
        return Jwts.builder()
           //     .signWith(new SecretKeySpec(SignatureAlgorithm.HS512.getJcaName(),secretKey.getBytes()))   // HS512 알고리즘을 사용하여 secretKey를 이용해 서명
                .signWith(SignatureAlgorithm.HS512,KeyByte)  // HS512 알고리즘을 사용하여 secretKey를 이용해 서명
                .setSubject(userSpecification)  // JWT 토큰 제목
                .setIssuer(issuer)  // JWT 토큰 발급자
                .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))    // JWT 토큰 발급 시간
                .setExpiration(Date.from(Instant.now().plus(expirationHours, ChronoUnit.HOURS)))    // JWT 토큰 만료 시간
                .compact(); // JWT 토큰 생성
    }

    public String validateTokenAndGetSubject(String token) {
        log.info("토큰검증 : "+ token);
        byte KeyByte[] = Base64.getDecoder().decode(secretKey);
        return Jwts.parser()
                .setSigningKey(KeyByte)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}