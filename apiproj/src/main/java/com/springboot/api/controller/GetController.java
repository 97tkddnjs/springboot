package com.springboot.api.controller;

import com.springboot.api.dto.MemberDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class GetController {

    @GetMapping("/hello")
//    @ResponseBody
    public String getHello() {
        return "Hello World";
    }

    @GetMapping("/test/{variable}")
    public String getTest(@PathVariable("variable") String var)
    {
        return var;
    }

    @GetMapping("/param")
    public String getParam(@RequestParam Map<String, String> param) {
        StringBuilder sb = new StringBuilder();
        // set으로 해서 -> map 바꾸거 -> foreach로 람다
        param.entrySet().forEach(map->{
            sb.append(map.getKey() +" : "+ map.getValue()+"\n");
        });
        return sb.toString();
    }

    @GetMapping("/member")
    public String getRequestMemberDto(MemberDto dto) {
        return dto.toString();
    }

    @ApiOperation(value = "GET 메서드 예제", notes="@RequestParam을 활용한 GET Method")
    @GetMapping("/request1")
    public String getRequestParam1(
        @ApiParam(value="이름",required = true) @RequestParam String name,
        @ApiParam(value="이메일",required = true) @RequestParam String email,
        @ApiParam(value="회사",required = true) @RequestParam String origanization
    ) {
        return name +" " + email +" "+ origanization;
    }
}

