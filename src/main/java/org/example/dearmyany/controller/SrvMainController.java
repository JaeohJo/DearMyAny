package org.example.dearmyany.controller;

import jakarta.servlet.http.HttpSession;
import org.example.dearmyany.repository.MemberRepository;
import org.example.dearmyany.service.SrvMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SrvMainController {

    @Autowired
    private SrvMainService srvMainService;

    @Autowired
    private MemberRepository memberRepository;

    // 게시판 메인 목록 페이지 (검색 + 페이징)
    @GetMapping("/srvMain")
    public String getPostList(HttpSession session,
                           @RequestParam(value = "page", defaultValue = "1") int page,
                           @RequestParam(value = "keyword", required = false) String keyword,
                           Model model) {

        SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
        Authentication auth = context.getAuthentication();
        String userEmail = auth.getName();
        String userName = memberRepository.findNameByEmail(userEmail);

        // 페이지 번호가 1 미만인 경우 기본값 1을 사용
        if (page < 1) {
            page = 1;
        }

        int size = 10;
        int totalPages = srvMainService.getTotalPages(size);
        if (page > totalPages) {
            page = totalPages;
        }

        // 페이지 번호는 0부터 시작하기 때문에 page - 1로 수정하여 전달
        model.addAttribute("posts", srvMainService.getPosts(keyword, page - 1, size));
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("username", userName);

        return "main/srvMain";  // 게시판 메인 뷰
    }

}
