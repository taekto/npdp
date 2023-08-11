package com.project.npdp.snslogin.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.npdp.member.entity.Gender;
import com.project.npdp.member.entity.Member;
import com.project.npdp.member.entity.OAuthType;
import com.project.npdp.member.entity.Role;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaverMember implements OAuthMember{
    private String email;
    private String nickname;
    private String profile_image;
    private String gender;
    private String birthday;
    private String birthyear;
    private String password;

    @Override
    public Member toMember(){
        Gender myGender = Gender.MALE;
        if(this.gender.equals("F")) myGender = Gender.FEMALE;

        Member member = Member.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .oauth(OAuthType.NAVER)
                .role(Role.MEMBER)
                .birth(String.format("%s-%s", birthyear, birthday))
                .gender(myGender)
                .build();
        return member;
    }
}
