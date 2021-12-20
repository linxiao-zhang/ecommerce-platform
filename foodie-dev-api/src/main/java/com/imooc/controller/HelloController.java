package com.imooc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//@Controller
@ApiIgnore
@RestController
public class HelloController {

    @GetMapping("/hello")
    public Object hello(){
        return "Hello World";
    }

    public Object setSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("userInfo","new user");
        session.setMaxInactiveInterval(3600);
        session.getAttribute("userInfo");
        session.removeAttribute("userInfo");
        return "ok";
    }
}
