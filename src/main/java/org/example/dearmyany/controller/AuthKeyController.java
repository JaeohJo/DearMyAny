package org.example.dearmyany.controller;

import lombok.RequiredArgsConstructor;
import org.example.dearmyany.service.AuthKeyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth-keys")
public class AuthKeyController {

    private final AuthKeyService authKeyService;

    @PostMapping("/generate")
    public ResponseEntity<String> generateAuthKeys() {
        authKeyService.generateAuthKeys(30);
        return ResponseEntity.status(HttpStatus.CREATED).body("인증키 생성 성공!");
    }
}
