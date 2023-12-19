package com.vti.blogapp.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.HashMap;

@ControllerAdvice // Xử lý ngoại lệ thì cần @ này
public class ErrorHandler extends ResponseEntityExceptionHandler // gõ methodAgrument
                        implements MessageSourceAware,
                                   AuthenticationEntryPoint {
    private MessageSource messageSource; // messageSource cho phép mình lấy ra giá trị dựa trên 1 key cho trước

    @Override
    public void setMessageSource(MessageSource messageSource) { // gõ set là ra
        this.messageSource = messageSource;
    }

    public String getMessage(String code) {
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var message = "Sorry! Invalid input";
        var errors = new HashMap<String, String>();
        for (var error : exception.getFieldErrors()) { // FieldErrors là ~ lỗi liên quan đến key-value
            var key = error.getField(); // trường dữ liệu
            var value = error.getDefaultMessage();
            errors.put(key, value);
        }
        var response = new ErrorResponse(message, errors);
        return new ResponseEntity<>(response, headers, status);
    } // Đây là phương thức cần ghi đè của lớp cha để xử lý

    @ExceptionHandler(value = ConstraintViolationException.class) // truyền vào 1 value có kiểu dữ liệu của exception đó
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException exception) {
        var message = "Sorry! Invalid input";
        var errors = new HashMap<String, String>();
        for (var constraint : exception.getConstraintViolations()) {
            var key = constraint.getPropertyPath().toString();
            var value = constraint.getMessage();
            errors.put(key, value);
        }
        var response = new ErrorResponse(message, errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // Bad Request nghĩa là lỗi do người dùng nhập/yêu câu sai
    }

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException Exception)
            throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        var message = getMessage("AuthenticationException.message");
        var error = new ErrorResponse(message);
        var out = response.getOutputStream();
        new ObjectMapper().writeValue(out, error);
    }
}
