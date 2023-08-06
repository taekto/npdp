package com.project.npdp.refregirator.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class MemberSeasoningModifyRequestDto {

    private Long memberId;

    private Long memberSeasoningId;

    private Long storage;

    private LocalDateTime startDate;

    private LocalDateTime expiredDate;

    private boolean isdelete;
}
