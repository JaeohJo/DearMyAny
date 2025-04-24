package org.example.dearmyany.dto;

import lombok.*;
import org.example.dearmyany.entity.Post;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {
    private Long postSeq;
    private String title;
    private Long writerSeq;
    private LocalDateTime createdAt;

    // Post 엔티티를 받아서 PostDto로 변환하는 생성자 추가
    public PostDto(Post post) {
        this.postSeq = post.getPostSeq();
        this.title = post.getTitle();
        this.writerSeq = post.getWriterSeq();
        this.createdAt = post.getCreatedAt();
    }
}
