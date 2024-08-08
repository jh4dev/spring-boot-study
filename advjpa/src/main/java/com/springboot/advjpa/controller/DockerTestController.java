package com.springboot.advjpa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/docker/test")
public class DockerTestController {

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        log.info("Hello Docker ~~~");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Hello Docker");
    }
}
