package com.project.npdp.member.dto.request;

import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
@Builder
public class MemberUtensilRequestDto {

    private Long memberId;

    private List<Long> utensilId;

}
