package com.project.npdp.refregirator.dto.response;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class MemberIngredientFindResponseDto {

    private Long ingredientId;

    private String name;

}
