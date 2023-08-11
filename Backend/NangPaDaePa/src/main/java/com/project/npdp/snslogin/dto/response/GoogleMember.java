package com.project.npdp.snslogin.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.npdp.member.entity.Member;
import com.project.npdp.member.entity.OAuthType;
import com.project.npdp.member.entity.Role;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleMember implements OAuthMember{
    private String email;
    private String name;
    private String password;

    public Member toMember(){
        Member member = Member.builder()
                .email(email)
                .nickname(name)
                .password(password)
                .oauth(OAuthType.GOOGLE)
                .role(Role.MEMBER)
                .build();
        return member;
    }
}
