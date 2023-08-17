package com.project.npdp.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.npdp.domain.Calendar;
import com.project.npdp.domain.MemberRecommend;
import com.project.npdp.refregirator.entity.MemberSeasoning;
import com.project.npdp.refregirator.entity.Refregirator;
import com.project.npdp.utils.SHA256Util;
import lombok.*;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String email;

    private String nickname;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OAuthType oauth = OAuthType.LOCAL;

//    @Column(nullable = false, columnDefinition = "VARCHAR(10) DEFAULT 'USER'")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.MEMBER;

    private String birth;

    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.MALE;

    // 탈퇴 여부
    private LocalDateTime quit;

    // member_utensil(회원보유장비) 연관관계
    @OneToMany(mappedBy = "member")
    private List<MemberUtensil> memberUtensilList = new ArrayList<>();


    // refregirator(냉장고) 연관관계
    @OneToMany(mappedBy = "member")
    private List<Refregirator> refregiratorList = new ArrayList<>();

    // member_seasoning(양념칸) 연관관계
    @OneToMany(mappedBy = "member")
    private List<MemberSeasoning> memberSeasoningList = new ArrayList<>();

    // member_allergy(회원알러지정보) 연관관계
    @OneToMany(mappedBy = "member")
    private List<MemberAllergy> memberAllergyList = new ArrayList<>();

    // member_dislike_ingredient(회원비선호재료) 연관관계
    @OneToMany(mappedBy = "member")
    private List<MemberDislikeIngredient> memberDislikeIngredientList = new ArrayList<>();

    // calendar(캘린더) 연관관계
    @OneToMany(mappedBy = "member")
    private List<Calendar> calendarList = new ArrayList<>();

    // member_recipe_like(회원레시피좋아요) 연관관계
    @OneToMany(mappedBy = "member")
    private List<MemberRecipeLike> memberRecipeLikeList = new ArrayList<>();

    // member_recipe_latest(회원최근본레시피) 연관관계
    @OneToMany(mappedBy = "member")
    private List<MemberRecipeLatest> memberRecipeLatestList = new ArrayList<>();

    // member_recommend(회원추천) 연관관계
    @OneToMany(mappedBy = "member")
    private List<MemberRecommend> memberRecommendList = new ArrayList<>();

    public String getRoleKey(){
        return this.role.getKey();
    }

    // 로그인 검증
    public boolean authenticate(String email, String password){

        String sha256Pw = SHA256Util.getSHA256(password);

        return this.email.equals(email) && this.password.equals(sha256Pw);
    }

//    @Builder
//    public Member(String email, String password, String nickname, Gender gender, String birth){
//        this.email = email;
//        this.password = password;
//        this.nickname = nickname;
//        this.gender = gender;
//        this.birth = birth;
//    }

    @Builder
    public Member(String email, String nickname, String password, OAuthType oauth, Role role, String birth, Gender gender){
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.oauth = oauth;
        this.role = role;
        this.birth = birth;
        this.gender = gender;
    }

    // 비밀번호 변경
    public void modifyPw(String newPassword){
        this.password = newPassword;
    }

    // 닉네임변경
    public void modifyNickname(String newNickname) {
        this.nickname = newNickname;
    }

    // 성별변경
    public void modifyGender(Gender newGender) {
        this.gender = newGender;
    }

    // 생일변경
    public void modifyBirth(String newBirth) {
        this.birth = newBirth;
    }

    public Member(Long id){
        this.id = id;
    }

    // 탈퇴여부 변경
    public void modifyQuit(LocalDateTime quitDate){
        this.quit = quitDate;
    }

}
