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

    // PASSWORD 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }

    @Bean
    public UserDetailsService userDetailsService(MemberRepository memberRepository) {
        return new CustomUserDetailsService(memberRepository);
    }

    // HTTP 보안 설정 (Spring Security 6.1 이상)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/",
                                "/main",
                                "/login",
                                "/login-page",
                                "/check-email",
                                "/register",
                                "/css/**",
                                "/js/**").permitAll()  // 로그인, 회원가입 페이지는 인증 없이 접근 가능
                        .anyRequest().authenticated()  // 나머지 페이지는 인증 필요
                )
                .formLogin(form -> form
                        .loginPage("/login-page")  // 커스텀 로그인 페이지 경로
                        .loginProcessingUrl("/login")  // 로그인 처리 URL
                        .usernameParameter("email") // ← 폼에서 사용한 name과 일치해야 함
                        .passwordParameter("password")
                        .defaultSuccessUrl("/srvMain", true)  // 로그인 성공 후 이동할 페이지
                        .failureUrl("/login-page?error=true")  // 로그인 실패 후 이동할 페이지
                        .permitAll()  // 로그인 페이지는 누구나 접근 가능하도록 설정
                )
                .logout(logout -> logout
                        .logoutUrl("/custom-logout")  // 커스텀 로그아웃 URL
                        .logoutSuccessUrl("/login?logout=true")  // 로그아웃 후 이동할 페이지
                );

        return http.build();
    }
}
