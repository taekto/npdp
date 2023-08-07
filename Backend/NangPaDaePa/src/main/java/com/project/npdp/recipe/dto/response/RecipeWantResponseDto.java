package com.project.npdp.recipe.dto.response;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class RecipeWantResponseDto {

    private Long recipeId;

    private String name;

    private String imgBig;
}

