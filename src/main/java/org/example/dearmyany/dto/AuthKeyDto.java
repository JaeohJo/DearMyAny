package org.example.dearmyany.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthKeyDto {

    private Long id;
    private String authKey;
    private boolean used;
    private LocalDateTime createdAt;
}