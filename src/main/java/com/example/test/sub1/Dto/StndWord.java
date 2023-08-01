package com.example.test.sub1.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Schema(description = "표준단어 DTO")
@Data
@Entity
@Table(name = "TB_STND_WORD")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@RequiredArgsConstructor
public class StndWord {

    @Schema(description = "표준단어ID DTO")
    @Id
    private String wordId;
    @Schema(description = "표준단어명 DTO")
    private String wordKorNm;
    @Schema(description = "표준단어영문명 DTO")
    private String wordEngNm;
    @Schema(description = "표준단어약어명 DTO")
    private String wordAbrNm;
    @Schema(description = "표준단어설명 DTO")
    private String wordDescn;
    @Schema(description = "등록일시 DTO")
    @CreationTimestamp
    private String regDtm;

}
