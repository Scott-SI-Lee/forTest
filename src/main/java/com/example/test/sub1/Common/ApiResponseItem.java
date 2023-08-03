package com.example.test.sub1.Common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponseItem<T> {

    private static final String SUCCESS_STATUS = "success";
    private static final String FAIL_STATUS = "fail";
    private static final String ERROR_STATUS = "error";

    private String status;
    private String code;
    private T data;
    private String message;

    public static <T> ApiResponseItem<T> createSuccess(String code, T data) {
        return new ApiResponseItem<>(code, SUCCESS_STATUS, data, null);
    }

    public static ApiResponseItem<?> createSuccessWithNoContent() {
        return new ApiResponseItem<>("200",SUCCESS_STATUS, null, null);
    }

    // Hibernate Validator에 의해 유효하지 않은 데이터로 인해 API 호출이 거부될때 반환
    public static ApiResponseItem<?> createFail(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();

        List<ObjectError> allErrors = bindingResult.getAllErrors();
        for (ObjectError error : allErrors) {
            if (error instanceof FieldError) {
                errors.put(((FieldError) error).getField(), error.getDefaultMessage());
            } else {
                errors.put( error.getObjectName(), error.getDefaultMessage());
            }
        }
        return new ApiResponseItem<>("500", FAIL_STATUS, errors, null);
    }

    // 예외 발생으로 API 호출 실패시 반환
    public static ApiResponseItem<?> createError(String code, String message) {
        return new ApiResponseItem<>(code, ERROR_STATUS, null, message);
    }

    private ApiResponseItem(String code, String status, T data, String message) {
        this.code = code;
        this.status = status;
        this.data = data;
        this.message = message;
    }
}