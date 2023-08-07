package com.project.npdp.member.dto.request;

import lombok.*;
import org.springframework.security.core.Authentication;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
@Builder
public class MemberLoginRequestDto {

    private String email;

    private String password;
}
