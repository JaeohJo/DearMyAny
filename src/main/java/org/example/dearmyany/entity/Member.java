package org.example.dearmyany.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               // Getter + Setter + toString + equals/hashCode
@NoArgsConstructor  // 기본 생성자
@AllArgsConstructor // 전체 필드 생성자
@Builder            // builder 패턴
@Entity            // JPA 엔티티 클래스
@Table(name = "member")  // 테이블 이름을 'member'로 지정
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동 증가
    private long memberSeq;

    private String name;
    private String phone;
    private String email;
    private String password;
    private String authKey;
}