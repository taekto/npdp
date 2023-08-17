package com.project.npdp.recipe.dto.request;

import com.project.npdp.recipe.dto.response.RecipeIngredientDetailDto;
import com.project.npdp.recipe.dto.response.RecipeSeasoningDetailDto;
import com.project.npdp.recipe.dto.response.RecipeSequenceDetailDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeDetailRequestDto {

    private Long recipeId;

    private Long memberId;

}
