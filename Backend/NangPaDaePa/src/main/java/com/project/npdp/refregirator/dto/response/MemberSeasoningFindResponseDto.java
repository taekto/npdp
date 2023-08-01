package com.project.npdp.refregirator.dto.response;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class MemberSeasoningFindResponseDto {

    private Long seasoningId;

    private String name;

}
