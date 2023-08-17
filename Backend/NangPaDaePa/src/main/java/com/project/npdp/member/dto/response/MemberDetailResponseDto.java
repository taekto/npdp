package com.project.npdp.member.dto.response;

import com.project.npdp.member.entity.Gender;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MemberDetailResponseDto {

    private String email;

    private String nickname;

    private Gender gender;

    private String birth;

}
