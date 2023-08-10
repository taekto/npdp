package com.project.npdp.member.dto.request;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Data
public class EmailVerityRequestDto {

    private String email;

    private String code;

}
