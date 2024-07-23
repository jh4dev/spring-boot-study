package com.springboot.api.controller;

import com.springboot.api.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {

    /**
     * RequsetMapping 사용
     * Spring 4.3 버전 이후로는 더 이상 사용되지 않음
     * */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String getHello() {
        return "Hello, This is Get Method";
    }
    
    /**
     * GetMapping
     * 1) 매개변수가 없는 Get Method 구현
     * */
    @GetMapping(value = "/name")
    public String getName() {
        return "My name is A";
    }
    
    /**
     * GetMapping
     * 2-1) PathVariable 을 활용한 Get Method
     * */
    @GetMapping(value = "/variable1/{variable}")
    public String getVariable1(@PathVariable String variable) {
        return variable;
    }

    /**
     * GetMapping
     * 2-2) PathVariable 을 활용한 Get Method
     * PathVariable 어노테이션 내, value 지정하여 받음
     * */
    @GetMapping(value = "/variable2/{variable}")
    public String getVariable2(@PathVariable("variable") String var) {
        return var;
    }
    
    
    /**
     * GetMapping
     * 3) RequestParam 을 활용한 Get Method
     * 정해진 파라미터
     * */
    @GetMapping(value = "/request1")
    public String getRequestParam1(@RequestParam String name, @RequestParam String email, @RequestParam String organization) {
        StringBuilder builder = new StringBuilder();
        builder.append("name : ")
                .append(name)
                .append("\n")
                .append("email : ")
                .append(email)
                .append("\n")
                .append("organization : ")
                .append(organization);

        return builder.toString();
    }

    /**
     * GetMapping
     * 3) RequestParam 을 활용한 Get Method
     * 불특정 파라미터
     * */
    @GetMapping(value = "/request2")
    public String getRequestParam2(@RequestParam Map<String, String> param) {
        StringBuilder builder = new StringBuilder();

        for(Map.Entry<String, String> entry : param.entrySet()) {
            builder.append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
        }

        return builder.toString();
    }

    /**
     * GetMapping
     * 3) Dto 를 활용한 Get Method
     * */
    @GetMapping(value = "/request3")
    public String getRequestParam3(MemberDto dto) {
        return dto.toString();
    }
}
