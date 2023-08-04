package com.project.npdp.member.dto.request;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Data
public class MemberGenderRequestDto {

    private Long id;

    private String gender;

}
