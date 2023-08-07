package com.project.npdp.refregirator.dto.response;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class IngredientFindResponseDto {

    Long ingredientId;
    String kor;

}
