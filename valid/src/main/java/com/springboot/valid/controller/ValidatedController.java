package com.springboot.valid.controller;

import com.springboot.valid.data.dto.ValidRequestDto;
import com.springboot.valid.data.dto.ValidatedRequestDto;
import com.springboot.valid.data.valid.group.ValidationGroup1;
import com.springboot.valid.data.valid.group.ValidationGroup2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/validation")
@Slf4j
public class ValidatedController {
    //@VALID
//    @PostMapping("/valid")
//    public ResponseEntity<ValidRequestDto> checkValidationByValidAnno(@Valid @RequestBody ValidRequestDto validRequestDto) {
//
//        log.info(validRequestDto.toString());
//
//        return ResponseEntity.status(HttpStatus.OK).body(validRequestDto);
//    }

    @PostMapping("/validated/group1")
    public ResponseEntity<ValidatedRequestDto> checkValidationByValidatedAnnoGroup1(
            @Validated(ValidationGroup1.class) @RequestBody ValidatedRequestDto validatedRequestDto
    ) {
        log.info(validatedRequestDto.toString());

        return ResponseEntity.status(HttpStatus.OK).body(validatedRequestDto);
    }

    @PostMapping("/validated/group2")
    public ResponseEntity<ValidatedRequestDto> checkValidationByValidatedAnnoGroup2(
            @Validated(ValidationGroup2.class) @RequestBody ValidatedRequestDto validatedRequestDto
    ) {
        log.info(validatedRequestDto.toString());

        return ResponseEntity.status(HttpStatus.OK).body(validatedRequestDto);
    }

    @PostMapping("/validated/all-group")
    public ResponseEntity<ValidatedRequestDto> checkValidationByValidatedAnnoAllGroup(
            @Validated({ValidationGroup1.class, ValidationGroup2.class})
            @RequestBody ValidatedRequestDto validatedRequestDto
    ) {
        log.info(validatedRequestDto.toString());

        return ResponseEntity.status(HttpStatus.OK).body(validatedRequestDto);
    }
}
