package com.project.npdp.recipe.dto.response;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class RecipeSeasoningDetailDto {

    private Long amount;

    private String unit;

    private Long seasoningId;

    private String code;

    private String kor;

    private String eng;

    private String def;

    private Long level;

    private String upperClass;

    private String superUpperClass;

    private String etc;

}
