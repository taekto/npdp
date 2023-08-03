package com.project.npdp.refregirator.dto.request;

import com.project.npdp.food.entity.Ingredient;
import com.project.npdp.food.repository.IngredientRepository;
import com.project.npdp.member.entity.Member;
import com.project.npdp.member.repository.MemberRepository;
import com.project.npdp.refregirator.entity.Refregirator;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.NoSuchElementException;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class MemberIngredientSaveRequestDto {

    private Long ingredientId;

    private Long storage;

    private Double amount;

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
