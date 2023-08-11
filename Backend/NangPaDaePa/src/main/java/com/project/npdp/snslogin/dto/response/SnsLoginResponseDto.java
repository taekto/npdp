package com.project.npdp.snslogin.dto.response;

import com.project.npdp.member.dto.response.MemberLoginResponseDto;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
public class SnsLoginResponseDto {
    private MemberLoginResponseDto memberLoginResponseDto;
    private HttpStatus httpStatus;
}
