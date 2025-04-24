package org.example.dearmyany.controller;

import org.example.dearmyany.dto.PostDto;
import org.example.dearmyany.service.SrvMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SrvMainController {

    @Autowired
    private SrvMainService srvMainService;

    // 게시판 메인 목록 페이지 (검색 + 페이징)
    @GetMapping("/srvMain")
    public String getBoard(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "keyword", required = false) String keyword,
            Model model) {

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

        return "srvMain";  // 게시판 메인 뷰
    }

    // 게시글 상세보기
    @GetMapping("/board/{postSeq}")
    public String getPostById(@PathVariable Long postSeq, Model model) {
        PostDto postDto = srvMainService.getPostById(postSeq);
        model.addAttribute("post", postDto);
        return "post-detail";
    }

    // 게시글 작성 폼
    @GetMapping("/board/new")
    public String createPostForm(Model model) {
        model.addAttribute("post", new PostDto());
        return "post-form";
    }
}
