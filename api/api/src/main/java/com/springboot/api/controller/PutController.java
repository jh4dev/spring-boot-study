package com.springboot.api.controller;

import com.springboot.api.dto.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/put-api")
public class PutController {

    /**
     * PutMapping
     * 1) RequestBody 어노테이션을 활용한 PUT 메서드
     * Map 활용
     * */
    @PutMapping(value = "/member")
    public String putMemberMap(@RequestBody Map<String, Object> postData) {
        StringBuilder builder = new StringBuilder();
        postData.entrySet().forEach(
                entry -> builder.append(entry.getKey()).append(" : ").append(entry.getValue())
        );

        return builder.toString();
    }

    /**
     * PutMapping
     * 1) RequestBody 어노테이션을 활용한 PUT 메서드
     * Dto 활용
     * */
    @PutMapping(value = "/member2")
    public String putMemberDto2(@RequestBody MemberDto member) {
        return member.toString();
    }

    /**
     * PutMapping
     * 1) RequestBody 어노테이션을 활용한 PUT 메서드
     * return DTO
     * */
    @PutMapping(value = "/member3")
    public MemberDto putMemberDto3(@RequestBody MemberDto member) {
        MemberDto m = new MemberDto();
        m.setName(member.getName());
        m.setEmail(member.getEmail());
        m.setOrganization(member.getOrganization());

        return m;
    }

    /**
     * PutMapping
     * 2) ResponseEntity 타입으로 리턴하는 PUT 메서드
     * return DTO
     * */
    @PutMapping(value = "/member4")
    public ResponseEntity<MemberDto> putMemberDto4(@RequestBody MemberDto member) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(member);
    }
}
