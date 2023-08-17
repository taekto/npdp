package com.project.npdp.recipe.dto.response;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class RecipeSequenceDetailDto {

    private Long sequence;

    private String description;

    private String img;

}
