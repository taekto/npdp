package com.project.npdp.recipe.dto.response;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class RecipeHeartResponseDto {

    private Long recipeId;

    private String name;

    private String imgBig;

    private String imgSmall;

    private String category;

    private Long count;

}
