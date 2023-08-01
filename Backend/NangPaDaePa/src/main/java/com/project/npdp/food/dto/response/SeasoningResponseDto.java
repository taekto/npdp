package com.project.npdp.food.dto.response;

import lombok.*;

@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class SeasoningResponseDto {
    private Long id;
    private String name;
}
