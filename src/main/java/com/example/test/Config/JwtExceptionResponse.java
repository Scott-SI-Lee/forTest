package com.example.test.Config;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
class JwtExceptionResponse {
    String message;
    HttpStatus status;

    public String convertToJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }
}