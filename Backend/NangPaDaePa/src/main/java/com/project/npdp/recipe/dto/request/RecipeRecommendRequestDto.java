package com.project.npdp.recipe.dto.request;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class RecipeRecommendRequestDto {

    private Long recipeOwnId;

}
