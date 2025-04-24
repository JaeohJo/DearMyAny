package org.example.dearmyany.controller;

import org.example.dearmyany.dto.MemberDto;
import org.example.dearmyany.service.AuthKeyService;
import org.example.dearmyany.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private LoginService loginService;

    private AuthKeyService authKeyService;

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
        // 유효키 검증
        boolean isValidKey = authKeyService.isValidKey(memberDto.getAuthKey());
        if (!isValidKey) {
            model.addAttribute("message", "유효하지 않은 인증키입니다.");
            return "register"; // 회원가입 페이지로 다시 이동
        }
        // 회원가입
        Boolean result = loginService.register(memberDto);
        if(result){
            // 유효키 사용처리
            //authKeyService.markKeyAsUsed(memberDto.getAuthKey());
            model.addAttribute("message", "회원가입이 완료되었습니다!");
            return "redirect:/login-page";
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

    @GetMapping("/login-page")
    public String login(Model model, @RequestParam(required = false) String error) {
        model.addAttribute("memberDto", new MemberDto());
        if (error != null) {
            model.addAttribute("errorMessage", "이메일과 비밀번호를 확인하세요.");
        }
        return "login-page";
    }

    @GetMapping("/logout")
    public String logout(){
        return "main";
    }
}
