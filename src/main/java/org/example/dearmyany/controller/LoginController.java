package org.example.dearmyany.controller;

import org.example.dearmyany.dto.MemberDto;
import org.example.dearmyany.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private LoginService loginService;

    @Autowired
    LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "register";
    }

    @PostMapping("/register")
    public String submitRegister(@ModelAttribute MemberDto memberDto, Model model) {
        Boolean result = loginService.register(memberDto);

        if(result){
            model.addAttribute("message", "회원가입이 완료되었습니다!");
            return "redirect:/main";
        }else{
            model.addAttribute("message", "회원가입에 실패하였습니다.");
            return "register";
        }
    }

    @GetMapping("/check-email")
    @ResponseBody
    public boolean checkEmail(@RequestParam String email) {
        return loginService.duplicateCheck(email);
    }

    @GetMapping("/login")
    public String login(Model model) {

        model.addAttribute("memberDto", new MemberDto());
        return "login";
    }

    @PostMapping("/login")
    public String tryLogin(@ModelAttribute MemberDto memberDto, Model model) {
        Boolean result = loginService.doLogin(memberDto.getEmail(), memberDto.getPassword());
        if(result){
            return "redirect:/loginMain";
        }else{
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(){
        return "main";
    }
}
