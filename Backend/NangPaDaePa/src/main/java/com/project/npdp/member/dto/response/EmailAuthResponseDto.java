package com.project.npdp.member.dto.response;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
@Builder
public class EmailAuthResponseDto {

    private String code;

}
