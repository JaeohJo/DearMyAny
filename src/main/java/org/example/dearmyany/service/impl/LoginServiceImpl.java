package org.example.dearmyany.service.impl;

import jakarta.servlet.http.HttpSession;
import org.example.dearmyany.dto.MemberDto;
import org.example.dearmyany.entity.Member;
import org.example.dearmyany.repository.MemberRepository;
import org.example.dearmyany.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HttpSession session;

    @Override
    public Boolean register(MemberDto memberDto){
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(memberDto.getPassword());

        // MemberDto를 Member 엔티티로 변환
        Member member = Member.builder()
                .name(memberDto.getName())
                .phone(memberDto.getPhone())
                .email(memberDto.getEmail())
                .password(encodedPassword)
                .authKey(memberDto.getAuthKey())
                .build();

        // 엔티티를 DB에 저장
        memberRepository.save(member);
        return true; // 성공적으로 등록되면 true 반환
    }

    // 이메일 중복검증 메소드
    @Override
    public Boolean duplicateCheck(String email){
        Optional<Member> member = memberRepository.findByEmail(email);
        return member.isPresent(); // 있으면 true, 없으면 false
    }

    @Override
    public Boolean doLogin(String email, String password){
        // 이메일로 회원 검색
        Optional<Member> memberOptional = memberRepository.findByEmail(email);
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            // 비밀번호 비교
            if(passwordEncoder.matches(password, member.getPassword())){
                session.setAttribute("user", member);
                return true;
            };
        }
        return false;  // 사용자 없음
    }

    public Member getLoggedInMember() {
        return (Member) session.getAttribute("user");
    }
}
