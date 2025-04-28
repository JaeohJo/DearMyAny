package org.example.dearmyany.config;

import org.example.dearmyany.repository.MemberRepository;
import org.example.dearmyany.security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 사용자 정보 서비스
    @Bean
    public UserDetailsService userDetailsService(MemberRepository memberRepository) {
        return new CustomUserDetailsService(memberRepository);
    }

    // 보안 필터 체인
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 인가 설정
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/", "/main", "/login", "/login-page", "/check-email", "/register",
                                "/css/**", "/js/**", "/api/auth-keys/generate"
                        ).permitAll() // 인증 없이 접근 가능한 경로
                        .anyRequest().authenticated() // 나머지 요청은 인증 필요
                )

                // 로그인 설정
                .formLogin(form -> form
                        .loginPage("/login-page") // 커스텀 로그인 페이지
                        .loginProcessingUrl("/login") // 로그인 처리 URL
                        .usernameParameter("email") // 로그인 폼의 name 속성
                        .passwordParameter("password")
                        .defaultSuccessUrl("/srvMain", true) // 로그인 성공 시 이동할 경로
                        .failureUrl("/login-page?error=true") // 로그인 실패 시 이동할 경로
                        .permitAll()
                )

                // 로그아웃 설정
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/main")
                        .invalidateHttpSession(true) // 세션 무효화
                        .deleteCookies("JSESSIONID") // 쿠키 삭제
                        .permitAll()
                )

                // 세션 관리 설정
                .sessionManagement(session -> session
                        .sessionFixation().migrateSession()
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(false)
                );

        return http.build();
    }
}
