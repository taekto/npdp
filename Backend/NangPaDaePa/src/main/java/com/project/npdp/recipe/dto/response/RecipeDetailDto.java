package com.project.npdp.recipe.dto.response;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class RecipeDetailDto {

    private Long RecipeId;

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

}
