package org.example.dearmyany.service;

import org.example.dearmyany.dto.AuthKeyDto;

import java.util.List;

public interface AuthKeyService {

    List<AuthKeyDto> generateAuthKeys(int count); // 랜덤 키 생성 및 저장

    boolean isValidKey(String authKey);           // 유효성 검사

    void markKeyAsUsed(String authKey);
}