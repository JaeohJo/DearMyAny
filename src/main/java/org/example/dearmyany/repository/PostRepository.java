package org.example.dearmyany.repository;

import org.example.dearmyany.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    // 제목에 검색어가 포함된 게시글을 찾는 메소드
    Page<Post> findByTitleContaining(String title, Pageable pageable);

}
