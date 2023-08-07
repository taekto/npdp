package com.project.npdp.refregirator.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class MemberIngredientModifyRequestDto {

    private Long memberId;

    private Long refregiratorId;

    private Long storage;

    private Double amount;

    private String unit;

    private LocalDateTime startDate;

    private LocalDateTime expiredDate;

    private boolean isdelete;

}
