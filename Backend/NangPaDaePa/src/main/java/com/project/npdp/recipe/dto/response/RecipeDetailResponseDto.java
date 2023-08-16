package com.project.npdp.recipe.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeDetailResponseDto {

    private Long recipeId;

    private String memberName;

    private String name;

    private Long way;

    private Double weight;

    private Double calorie;

    private Double carbohydrate;

    private Double protein;

    private Double fat;

    private Double salt;

    private String imgSmall;

    private String imgBig;

    private String category;

    private Long dish;

    private Long count;

    private Boolean heartTF;

    private Double similarity;

    private List<RecipeIngredientDetailDto> recipeIngredients;

    private List<RecipeSeasoningDetailDto> recipeSeasonings;

    private List<RecipeSequenceDetailDto> recipeSequences;

    private List<RecipeUtensilResponseDto> recipeUtensils;

}
