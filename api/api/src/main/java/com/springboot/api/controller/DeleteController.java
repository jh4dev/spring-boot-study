package com.springboot.api.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vi/delete-api")
public class DeleteController {

    @DeleteMapping("/{variable}")
    public String deleteVariable(@PathVariable String variable) {
        return variable;
    }

    @DeleteMapping(value = "/request1")
    public String getRequestParam1(@RequestParam String email) {
        return "email : " + email;
    }
}
