package com.project.npdp.recipe.dto.response;

import lombok.*;

@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class RecipeResponseDto {

    private Long id;

    private String name;

    private String imgBig;

    private String imgSmall;

    private String category;

    private Long likes;

}