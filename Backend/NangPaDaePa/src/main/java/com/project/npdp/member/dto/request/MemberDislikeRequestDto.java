package com.project.npdp.member.dto.request;

import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
@Builder
public class MemberDislikeRequestDto {

    private Long memberId;

    private List<Long> ingredientId;

}
