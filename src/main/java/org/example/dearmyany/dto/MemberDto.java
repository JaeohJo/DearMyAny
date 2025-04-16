package org.example.dearmyany.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               // Getter + Setter + toString + equals/hashCode
@NoArgsConstructor  // 기본 생성자
@AllArgsConstructor // 전체 필드 생성자
@Builder            // builder 패턴
public class MemberDto {

    @NotNull
    private String name;

    @NotNull
    private String phone;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String authKey;
}
