package com.project.npdp.refregirator.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class MemberSeasoningFindResponseDto {

    private Long memberSeasoningId;

    private String kor;

    private Long storage;

    private LocalDateTime startDate;

    private LocalDateTime expiredDate;

}
