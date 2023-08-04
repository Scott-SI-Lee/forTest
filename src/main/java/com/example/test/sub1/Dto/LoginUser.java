package com.example.test.sub1.Dto;

import com.example.test.sub1.Common.UserType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.User;

@Schema(description = "로그인DTO")
@Data
@Entity

@Table(name="TB_USER")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@RequiredArgsConstructor
public class LoginUser {
    @Schema(description = "유저ID")
    @Id
    private String userId;
    @Schema(description = "유저명")
    private String userNm;
    @Schema(description = "유저패스워드")
    private String userPw;

    @Schema(description = "유저타입")
    private String userType;
    @Schema(description = "토큰")
    @Transient  // 실제테이블에는 없고 dto로만 사용될 때
    private String token;

    @Schema(description = "리프레시토큰")
    private String refreshToken;

    public LoginUser(String userId, String userNm, UserType userType, String token,String refreshToken) {
        this.userId=userId;
        this.userNm=userNm;
        this.userType= userType.name();
        this.token=token;
        this.refreshToken=refreshToken;
    }
}
