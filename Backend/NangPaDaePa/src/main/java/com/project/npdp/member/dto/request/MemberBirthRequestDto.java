package com.project.npdp.member.dto.request;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Data
public class MemberBirthRequestDto {

    private Long memberId;

    private String birth;

}
