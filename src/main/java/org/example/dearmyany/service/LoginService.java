package org.example.dearmyany.service;

import org.example.dearmyany.dto.MemberDto;

public interface LoginService {

    public Boolean register(MemberDto memberDto);

    public Boolean duplicateCheck(String email);

    public Boolean doLogin(String email, String password);
}
