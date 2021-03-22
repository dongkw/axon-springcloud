package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author dongkw
 * @Date 2021/3/18、4:15 下午
 **/
@RestController
public class Controller {
    @GetMapping("/command")
    public String point() {
        return "success";
    }
}
