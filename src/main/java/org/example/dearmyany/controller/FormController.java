package org.example.dearmyany.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FormController {

    @GetMapping("/makeForm")
    public String makeForm(long formSeq) {

        return null;
    }
}
