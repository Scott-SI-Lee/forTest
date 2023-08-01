package com.example.test.sub1.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Schema(description = "게시물 DTO")
@Data
@Entity
@Table(name="TB_POST")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@RequiredArgsConstructor
public class Post {

    @Schema(description = "게시물ID DTO")
    @Id
    private String postId;
    @Schema(description = "게시물제목 DTO")
    private String postTit;
    @Schema(description = "게시물내용 DTO")
    private String postCntt;
    @Schema(description = "게시물등록시간 DTO")
    @CreationTimestamp
    private String postDtm;


}
