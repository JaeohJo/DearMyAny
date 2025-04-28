package org.example.dearmyany.controller;

import org.example.dearmyany.dto.PostDto;
import org.example.dearmyany.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    // 게시글 작성 폼
    @GetMapping("/createPostForm")
    public String createPostForm(Model model) {
        model.addAttribute("post", new PostDto());
        return "post/post-form";
    }

    // 게시글 상세보기
    @GetMapping("/{postSeq}")
    public String getPostById(@PathVariable Long postSeq, Model model) {
        PostDto postDto = postService.getPostById(postSeq);
        model.addAttribute("post", postDto);
        return "post/post-detail";
    }
}

