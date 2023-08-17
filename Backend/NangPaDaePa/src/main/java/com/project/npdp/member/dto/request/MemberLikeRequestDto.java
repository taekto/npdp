package com.project.npdp.member.dto.request;

import com.project.npdp.member.entity.MemberRecipeLike;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MemberLikeRequestDto {

    private Long memberId;

    private Long recipeId;

}
