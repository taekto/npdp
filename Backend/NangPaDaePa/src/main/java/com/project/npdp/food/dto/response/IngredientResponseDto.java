package com.project.npdp.food.dto.response;


import lombok.*;

@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Data
public class IngredientResponseDto {
    private Long id;
    private String name;
}
