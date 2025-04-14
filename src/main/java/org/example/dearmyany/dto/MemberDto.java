package org.example.dearmyany.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               // Getter + Setter + toString + equals/hashCode
@NoArgsConstructor  // 기본 생성자
@AllArgsConstructor // 전체 필드 생성자
@Builder            // builder 패턴
public class MemberDto {
    private String name;
    private String phone;
    private String email;
    private String password;
    private String authKey;

}
