package org.example.dearmyany.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SrvMainController {

    @GetMapping("/srvMain")
    public String srvMain(){

        return "srvMain";
    }
}
