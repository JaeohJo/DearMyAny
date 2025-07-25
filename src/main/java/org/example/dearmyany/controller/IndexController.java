package org.example.dearmyany.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String redirectToMain(){
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String main(){
        return "main/main";
    }
}
