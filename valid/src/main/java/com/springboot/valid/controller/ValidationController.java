package com.springboot.valid.controller;

import com.springboot.valid.data.dto.ValidRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/validation")
@Slf4j
public class ValidationController {

    @PostMapping("/valid")
    public ResponseEntity<ValidRequestDto> checkValidationByValidAnno(@Valid @RequestBody ValidRequestDto validRequestDto) {

        log.info(validRequestDto.toString());

        return ResponseEntity.status(HttpStatus.OK).body(validRequestDto);
    }


}
