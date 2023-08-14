package com.project.npdp.member.dto.response;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
@Builder
public class MemberAllergyResponseDto {

    private Long allergyId;

    private String allergyName;

}
