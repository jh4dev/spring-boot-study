package com.springboot.api.controller;

import com.springboot.api.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/post-api")
public class PostController {

    /**
     * RequsetMapping 사용
     * */
    @RequestMapping(value = "/domain", method = RequestMethod.POST)
    public String postExample()  {
        return "Hello Post Api";
    }

    /**
     * PostMapping
     * 1) RequestBody 어노테이션을 활용한 POST 메서드
     * Map 활용
     * */
    @PostMapping(value = "/member")
    public String postMemberMap(@RequestBody Map<String, Object> postData) {
        StringBuilder builder = new StringBuilder();
        postData.entrySet().forEach(entry -> builder.append(entry.getKey()).append(" : ").append(entry.getValue()));

        return builder.toString();
    }

    /**
     * PostMapping
     * 1) RequestBody 어노테이션을 활용한 POST 메서드
     * Dto 활용
     * */
    @PostMapping(value = "/member2")
    public String postMemberDto(@RequestBody MemberDto member) {
        return member.toString();
    }


}
