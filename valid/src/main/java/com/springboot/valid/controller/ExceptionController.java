package com.springboot.valid.controller;

import com.springboot.valid.common.Constants;
import com.springboot.valid.common.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController("/exception")
@Slf4j
public class ExceptionController {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleException(RuntimeException re, HttpServletRequest request){

        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        log.error("Controller 내 handleException() 호출, {}, {}", request.getRequestURI(), re.getMessage());

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("error_type", httpStatus.getReasonPhrase());
        responseMap.put("code", "400");
        responseMap.put("message", re.getMessage());

        return ResponseEntity.status(httpStatus).body(responseMap);
    }

    @GetMapping("/basic")
    public String basicExceptionController() throws RuntimeException {
        throw new RuntimeException("기본 Exception Handler 테스트");
    }

    @GetMapping("/custom")
    public void customExceptionController() throws CustomException {
        throw new CustomException(Constants.ExceptionClass.PRODUCT, HttpStatus.BAD_REQUEST, "Custom Exception Controller Test");
    }

}
