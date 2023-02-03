package com.springboot.api.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/delete-api")
public class DeleteController {

    @DeleteMapping("/{variable}")
    public String deleteVariable(@PathVariable String variable) {
        return variable;
    }

    @DeleteMapping("/request")
    public String getRequestParam(@RequestParam String email) {
        return "email : "+email;
    }
}
