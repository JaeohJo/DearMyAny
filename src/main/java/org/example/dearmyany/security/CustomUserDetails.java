package org.example.dearmyany.security;

import org.example.dearmyany.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final Member member;

    public CustomUserDetails(Member member) {
        this.member = member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 권한은 필요에 따라 구성
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return member.getPassword();  // Member 엔티티에서 비밀번호 반환
    }

    @Override
    public String getUsername() {
        return member.getEmail();  // 사용자명은 이메일로 설정
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Member getMember() {
        return member;
    }
}
