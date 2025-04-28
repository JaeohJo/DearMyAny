package org.example.dearmyany.service.impl;

import org.example.dearmyany.dto.PostDto;
import org.example.dearmyany.entity.Post;
import org.example.dearmyany.repository.PostRepository;
import org.example.dearmyany.service.SrvMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SrvMainServiceImpl implements SrvMainService {

    @Autowired
    private PostRepository postRepository;

    // 게시글 목록 조회 (검색어와 페이지 번호를 받음)
    @Override
    public List<PostDto> getPosts(String keyword, int page, int size) {
        if (page < 0) {
            page = 0;  // 페이지 번호는 0 이상이어야 합니다.
        }

        Pageable pageable = PageRequest.of(page, size);

        Page<Post> posts;
        if (keyword != null && !keyword.isEmpty()) {
            posts = postRepository.findByTitleContaining(keyword, pageable);  // 제목에 키워드를 포함하는 게시글 검색
        } else {
            posts = postRepository.findAll(pageable);  // 모든 게시글 가져오기
        }

        // 게시글이 없으면 빈 리스트 반환
        if (posts == null || posts.isEmpty()) {
            return new ArrayList<>();
        }

        return posts.stream().map(post -> new PostDto(post)).collect(Collectors.toList());
    }

    // 전체 페이지 수 조회
    @Override
    public int getTotalPages(int size) {
        long totalPosts = postRepository.count();
        return (int) Math.ceil((double) totalPosts / size);
    }

    // 게시글 생성
    @Override
    public void createPost(PostDto postDto) {
        Post post = Post.builder()
                .title(postDto.getTitle())
                .writerSeq(1L) // 예시: 작성자 ID는 하드코딩 또는 추가 로직 필요
                .build();
        postRepository.save(post);
    }
}
