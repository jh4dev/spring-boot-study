package com.springboot.valid.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleException(RuntimeException re, HttpServletRequest request){

        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        log.error("Advice 내 handleException() 호출, {}, {}", request.getRequestURI(), re.getMessage());

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("error_type", httpStatus.getReasonPhrase());
        responseMap.put("code", "400");
        responseMap.put("message", re.getMessage());

        return ResponseEntity.status(httpStatus).body(responseMap);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> validationExceptionHandler(MethodArgumentNotValidException e, HttpServletRequest request){

        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        log.error("Advice 내 validAnnoExceptionHandler() 호출, {}, {}", request.getRequestURI(), e.getMessage());

        String message = "Validation failed";

        FieldError fieldError = e.getBindingResult().getFieldError();
        if(fieldError != null) {
            message = fieldError.getDefaultMessage();
        }

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("error_type", httpStatus.getReasonPhrase());
        responseMap.put("code", "400");
        responseMap.put("message", message);

        return ResponseEntity.status(httpStatus).body(responseMap);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> validatedAnnoExceptionHandler(MethodArgumentNotValidException e, HttpServletRequest request){

        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        log.error("Advice 내 validatedAnnoExceptionHandler() 호출, {}, {}", request.getRequestURI(), e.getMessage());

        String message = "Validation failed";

        FieldError fieldError = e.getBindingResult().getFieldError();
        if(fieldError != null) {
            message = fieldError.getDefaultMessage();
        }

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("error_type", httpStatus.getReasonPhrase());
        responseMap.put("code", "400");
        responseMap.put("message", message);

        return ResponseEntity.status(httpStatus).body(responseMap);
    }

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<Map<String, String>> customExceptionHandler(CustomException e, HttpServletRequest request){

        log.error("Advice 내 customExceptionHandler() 호출, {}, {}", request.getRequestURI(), e.getMessage());

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("error_type", e.getHttpStatusType());
        responseMap.put("code", String.valueOf(e.getHttpStatusCode()));
        responseMap.put("message", e.getMessage());

        return ResponseEntity.status(e.getHttpStatus()).body(responseMap);
    }
}
