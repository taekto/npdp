package com.project.npdp.member.dto.response;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Data
public class MemberLoginResponseDto {

    private String nickname;

    private String accessToken;

}
