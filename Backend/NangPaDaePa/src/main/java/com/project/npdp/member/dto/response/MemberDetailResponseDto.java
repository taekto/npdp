package com.project.npdp.member.dto.response;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MemberDetailResponseDto {

    private String email;

    private String nickname;

    private String gender;

    private String birth;

}
