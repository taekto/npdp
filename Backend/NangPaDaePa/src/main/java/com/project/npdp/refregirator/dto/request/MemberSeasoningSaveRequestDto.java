package com.project.npdp.refregirator.dto.request;

import com.project.npdp.food.entity.Ingredient;
import com.project.npdp.food.entity.Seasoning;
import com.project.npdp.member.entity.Member;
import com.project.npdp.refregirator.entity.MemberSeasoning;
import com.project.npdp.refregirator.entity.Refregirator;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class MemberSeasoningSaveRequestDto {

    private Long seasoningId;

    private Long storage;

    private LocalDateTime startDate;

    public MemberSeasoning toEntity(MemberSeasoningSaveRequestDto memberIngredientSaveRequestDto, Member member, Seasoning seasoning) {
        MemberSeasoning result = MemberSeasoning.builder()
                .member(member)
                .seasoning(seasoning)
                .storage(memberIngredientSaveRequestDto.getStorage())
                .startDate(memberIngredientSaveRequestDto.getStartDate())
                .build();
        return result;
    }
}
