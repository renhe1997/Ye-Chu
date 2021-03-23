package com.example.lifedemo01.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: RenHe
 * @Date: 2020/11/6 17:17
 */
//这里的标签，RestController
@RestController
@RequestMapping("CA")
public class CartoonController {
    @RequestMapping("/cartoon")
    public String selectOne() {
        System.out.println("走cartoon");
        return "cartoon";
    }
}
