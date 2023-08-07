package com.project.npdp.member.dto.request;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
@Builder
public class EmailAuthRequestDto {

    private String email;

}
