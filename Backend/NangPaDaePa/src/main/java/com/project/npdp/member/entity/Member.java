package com.project.npdp.member.entity;

import com.project.npdp.refrigerator.entity.Refregirator;
import com.project.npdp.utensil.entity.MemberUtensil;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String email;

    private String nickname;

    private String password;

    private String oauth;

    private String refreshToken;

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

    // calendar(캘린더) 연관관계
    @OneToMany(mappedBy = "member")
    private List<Calendar> calendarList = new ArrayList<>();

    // member_recipe_like(회원레시피좋아요) 연관관계
    @OneToMany(mappedBy = "member")
    private List<MemberRecipeLike> memberRecipeLikeList = new ArrayList<>();

    // member_recipe_latest(회원최근본레시피) 연관관계
    @OneToMany(mappedBy = "member")
    private List<MemberRecipeLatest> memberRecipeLatestList = new ArrayList<>();

    // calendar(캘린더) 연관관계
    @OneToMany(mappedBy = "member")
    private List<MemberDislikeIngredient> memberDislikeIngredientList = new ArrayList<>();
}
