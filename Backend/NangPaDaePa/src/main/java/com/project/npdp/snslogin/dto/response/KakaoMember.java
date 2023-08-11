package com.project.npdp.snslogin.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.npdp.member.entity.Gender;
import com.project.npdp.member.entity.Member;
import com.project.npdp.member.entity.OAuthType;
import com.project.npdp.member.entity.Role;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoMember implements OAuthMember{
    private String nickname;
    private String email;
    private String birthyear;
    private String birthday;
    private String gender;
    private String password;
    @Override
    public Member toMember(){
        Gender myGender = Gender.MALE;
        if(gender.equals("female")) myGender = Gender.FEMALE;

        String myBirth = String.format("%s-%s", birthyear, birthday.replaceAll("(\\d{2})(\\d{2})", "$1-$2"));

        Member member = Member.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .birth(myBirth)
                .gender(myGender)
                .oauth(OAuthType.KAKAO)
                .role(Role.MEMBER)
                .build();
        return member;
    }
}
