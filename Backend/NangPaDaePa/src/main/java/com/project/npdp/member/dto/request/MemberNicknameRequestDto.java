package com.project.npdp.member.dto.request;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
@Builder
public class MemberNicknameRequestDto {

    private Long memberId;

    private String nickname;

}
