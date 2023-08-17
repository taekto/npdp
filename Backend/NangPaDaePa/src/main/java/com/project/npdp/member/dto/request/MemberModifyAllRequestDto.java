package com.project.npdp.member.dto.request;

import com.project.npdp.member.entity.Gender;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
@Builder
public class MemberModifyAllRequestDto {

    private Long memberId;

    private String password;

    private String nickname;

    private String birth;

    private Gender gender;

}
