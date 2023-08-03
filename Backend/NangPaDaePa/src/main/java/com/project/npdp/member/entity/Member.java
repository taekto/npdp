package com.project.npdp.member.entity;

import com.project.npdp.domain.Calendar;
import com.project.npdp.refregirator.entity.MemberSeasoning;
import com.project.npdp.refregirator.entity.Refregirator;
import lombok.*;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Getter
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;

    private String nickname;

    private String password;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private OAuthType oauth = OAuthType.LOCAL;

//    @Column(nullable = false, columnDefinition = "VARCHAR(10) DEFAULT 'USER'")
    @Builder.Default
    private String role = "USER";

    private String birth;

    private String gender;

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

}
