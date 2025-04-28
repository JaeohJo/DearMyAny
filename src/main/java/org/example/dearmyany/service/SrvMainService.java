package org.example.dearmyany.service;

import org.example.dearmyany.dto.PostDto;

import java.util.List;

public interface SrvMainService {

    // 게시글 목록 조회 (검색어와 페이지 번호를 받음)
    List<PostDto> getPosts(String keyword, int page, int size);

    // 전체 페이지 수 조회
    int getTotalPages(int size);

    // 게시글 생성
    void createPost(PostDto postDto);

}
