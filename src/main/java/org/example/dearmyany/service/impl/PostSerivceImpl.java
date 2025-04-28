package org.example.dearmyany.service.impl;

import org.example.dearmyany.dto.PostDto;
import org.example.dearmyany.entity.Post;
import org.example.dearmyany.repository.PostRepository;
import org.example.dearmyany.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostSerivceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    // 게시글 조회 (ID로)
    @Override
    public PostDto getPostById(Long postSeq) {
        Post post = postRepository.findById(postSeq)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        return new PostDto(post.getPostSeq(), post.getTitle(), null, post.getCreatedAt());
    }
}
