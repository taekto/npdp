package com.project.npdp.recipe.dto.request;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class FindAllRecipeWithConditionRequestDto {

    // 검색어
    private String searchWord;
    // 분류
    private String classification;
    // 키워드
    private String keyWord;

}
