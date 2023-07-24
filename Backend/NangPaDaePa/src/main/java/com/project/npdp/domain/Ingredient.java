package com.ssafy.npdp.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Getter
public class Ingredient {

    @Id
    @GeneratedValue
    @Column(name = "ingredient_id")
    private Long id;

    private String name;

    // refregirator(냉장고) 연관관계
    @OneToMany(mappedBy = "ingredient")
    private List<Refregirator> refregiratorList = new ArrayList<>();

    // recipe_ingredient(레시피포함재료) 연관관계
    @OneToMany(mappedBy = "ingredient")
    private List<RecipeIngredient> recipeIngredientList = new ArrayList<>();

    // member_allergy(회원알러지정보) 연관관계
    @OneToMany(mappedBy = "ingredient")
    private List<MemberAllergy> memberAllergyList = new ArrayList<>();

    // member_allergy(회원알러지정보) 연관관계
    @OneToMany(mappedBy = "ingredient")
    private List<MemberDislikeIngredient> memberDislikeIngredientList = new ArrayList<>();
}
