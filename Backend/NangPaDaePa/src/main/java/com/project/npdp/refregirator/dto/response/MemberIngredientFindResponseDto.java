package com.project.npdp.refregirator.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class MemberIngredientFindResponseDto {

    private Long refregiratorId;

    private String kor;

    private String amount;

    private String unit;

    private LocalDateTime startDate;

    private LocalDateTime expiredDate;

    private Long storage;

}
