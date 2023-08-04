package com.project.npdp.recipe.dto.response;

import com.project.npdp.member.entity.MemberRecipeLike;
import com.project.npdp.recipe.entity.RecipeIngredient;
import com.project.npdp.recipe.entity.RecipeSeasoning;
import com.project.npdp.recipe.entity.RecipeSequence;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class RecipeIdResponseDto {
    private String name;

    private Long way;

    private Double weight;

    private Double calorie;

    private Double carbohydrate;

    private Double protein;

    private Double fat;

    private Double salt;

    private String imgBig;

    private List<RecipeIngredient> recipeIngredientList;

    private List<RecipeSeasoning> recipeSeasoningList;

    private List<RecipeSequence> recipeSequenceList;
}