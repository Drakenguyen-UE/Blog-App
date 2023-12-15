package com.vti.blogapp.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class ErrorResponse { // Định nghĩa định dạng trả về của lỗi
    private String timestamp = LocalDateTime.now().toString(); // để trả về dạng string
                              // Có giá trị bằng giá trị tại thời điểm xảy ra lỗi
    private String message;
    private Map<String, String> errors;

    public ErrorResponse(String message) {
        this.message = message;
        this.errors = null;
    }

    public ErrorResponse(String message, Map<String, String> errors) {
        this.message = message;
        this.errors = errors;
    }
}
