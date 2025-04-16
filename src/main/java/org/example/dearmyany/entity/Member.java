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
    @Column(name = "member_seq") // 컬럼명 'member_seq'로 매핑
    private long memberSeq;

    @Column(nullable = false) // 'name' 컬럼은 NOT NULL로 설정
    private String name;

    @Column(nullable = false) // 'phone' 컬럼은 NOT NULL로 설정
    private String phone;

    @Column(nullable = false) // 'email' 컬럼은 NOT NULL로 설정
    private String email;

    @Column(nullable = false) // 'password' 컬럼은 NOT NULL로 설정
    private String password;

    @Column(name = "auth_key", nullable = false) // 'auth_key' 컬럼을 NOT NULL로 설정
    private String authKey;
}
