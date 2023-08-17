package com.project.npdp.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MemberLatestResponseDto {

    private Long recipeId;

    private String name;

    private String imgBig;

    private String imgSmall;

    private String category;

    private LocalDateTime date;

}
