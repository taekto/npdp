package com.project.npdp.member.dto.request;

import com.project.npdp.member.entity.Gender;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Data
public class MemberGenderRequestDto {

    private Long memberId;

    private String gender;

}
