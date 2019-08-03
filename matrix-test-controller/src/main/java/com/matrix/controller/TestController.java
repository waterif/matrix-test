package com.matrix.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author DuFeng
 * @date 2018/07/11 09:57:50
 */
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("helloworld")
    public String helloworld() {
        return "Hello world";
    }
}
