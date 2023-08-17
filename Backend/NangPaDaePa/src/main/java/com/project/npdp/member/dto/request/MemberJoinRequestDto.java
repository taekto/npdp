package com.project.npdp.member.dto.request;

import com.project.npdp.member.entity.Gender;
import com.project.npdp.member.entity.OAuthType;
import com.project.npdp.member.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MemberJoinRequestDto {

    private Long memberId;
    private String email;
    private String password;
    private String nickname;
    private String gender;
    private String birth;
    
}
