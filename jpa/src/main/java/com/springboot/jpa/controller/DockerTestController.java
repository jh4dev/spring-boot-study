package com.springboot.jpa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/docker/test")
public class DockerTestController {

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Hello Docker");
    }
}
