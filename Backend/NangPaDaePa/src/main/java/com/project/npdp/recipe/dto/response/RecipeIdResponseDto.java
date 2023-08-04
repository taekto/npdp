package com.project.npdp.recipe.dto.response;

import com.project.npdp.food.entity.Ingredient;
import com.project.npdp.food.entity.Seasoning;
import com.project.npdp.recipe.entity.Recipe;
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
        private List<RecipeSequence> recipeSequenceList;

//    @NoArgsConstructor(access = AccessLevel.PROTECTED)
//    @Builder
//    @AllArgsConstructor
//    public static class Recipe {
//        private String name;
//        private Long way;
//        private Double weight;
//        private Double calorie;
//        private Double carbohydrate;
//        private Double protein;
//        private Double fat;
//        private Double salt;
//        private String imgBig;
////        private Long sequence;
////        private String description;
////        private String img;
//
//    }
//
//    @NoArgsConstructor(access = AccessLevel.PROTECTED)
//    @Builder
//    @AllArgsConstructor
//    public static class RecipeIngredient {
//        private Ingredient ingredient;
//    }
//
//    @NoArgsConstructor(access = AccessLevel.PROTECTED)
//    @Builder
//    @AllArgsConstructor
//    public static class RecipeSeasoning {
//        private Seasoning seasoning;
//    }
//
//    @NoArgsConstructor(access = AccessLevel.PROTECTED)
//    @Builder
//    @AllArgsConstructor
//    public static class RecipeSequence {
//        private Long id;
//
//        private Long sequence;
//
//        private String description;
//
//        private String img;
//    }

}
