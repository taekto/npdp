package com.project.npdp.member.dto;

import com.project.npdp.member.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MemberJoinRequestDto {

    private Long id;
    private String email;
    private String password;
    private String nickname;
    private Gender gender;
    private String birth;

}
