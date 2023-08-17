package com.project.npdp.refregirator.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.npdp.food.entity.Ingredient;
import com.project.npdp.member.entity.Member;
import com.project.npdp.refregirator.entity.Refregirator;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class MemberIngredientSaveRequestDto {

    private Long ingredientId;

    private Long storage;

    private String amount;

    private String unit;

    private LocalDateTime startDate;

    private LocalDateTime expiredDate;

    public Refregirator toEntity(MemberIngredientSaveRequestDto memberIngredientSaveRequestDto, Member member, Ingredient ingredient) {
        Refregirator refregirator = Refregirator.builder()
                .storage(memberIngredientSaveRequestDto.getStorage())
                .amount(memberIngredientSaveRequestDto.getAmount())
                .unit(memberIngredientSaveRequestDto.getUnit())
                .startDate(memberIngredientSaveRequestDto.getStartDate())
                .expiredDate(memberIngredientSaveRequestDto.getExpiredDate())
                .member(member)
                .ingredient(ingredient)
                .build();
        return refregirator;
    }
}
