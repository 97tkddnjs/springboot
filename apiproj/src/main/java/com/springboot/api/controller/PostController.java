package com.springboot.api.controller;

import com.springboot.api.dto.MemberDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/post-api")
public class PostController {

    @RequestMapping("/domain")
    public String postExample() {
        return "Hello Post APi";
    }

    @PostMapping("/member")
    public String postMember(@RequestBody Map<String, String > postData) {
        StringBuilder sb = new StringBuilder();

        postData.entrySet().forEach(
                map -> {
                    sb.append(map.getKey()+" : "+ map.getValue()+"\n");
                }
        );
        return sb.toString();
    }

    @PostMapping("/member2")
    public String postMember2(@RequestBody MemberDto memberDto) {

        return memberDto.toString();
    }

}
