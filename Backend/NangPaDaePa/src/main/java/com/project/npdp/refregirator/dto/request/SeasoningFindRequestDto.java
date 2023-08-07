package com.project.npdp.refregirator.dto.request;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class SeasoningFindRequestDto {

    String keyword;

}
