package com.project.npdp.food.entity;

import com.project.npdp.domain.AllergyIngredient;
import com.project.npdp.refregirator.entity.Refregirator;
import com.project.npdp.member.entity.MemberDislikeIngredient;
import com.project.npdp.recipe.entity.RecipeIngredient;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private Long id;

    private String code;

    private String kor;

    private String eng;

    private String def;

    private Long level;

    private String upperClass;

    private String superUpperClass;

    private String etc;

    // refregirator(냉장고) 연관관계
    @OneToMany(mappedBy = "ingredient")
    private List<Refregirator> refregiratorList = new ArrayList<>();

    // recipe_ingredient(레시피포함재료) 연관관계
    @OneToMany(mappedBy = "ingredient")
    private List<RecipeIngredient> recipeIngredientList = new ArrayList<>();

    // member_allergy(회원알러지정보) 연관관계
    @OneToMany(mappedBy = "ingredient")
    private List<AllergyIngredient> allergyIngredientList = new ArrayList<>();

    // member_dislike_ingredient(회원비선호재료) 연관관계
    @OneToMany(mappedBy = "ingredient")
    private List<MemberDislikeIngredient> memberDislikeIngredientList = new ArrayList<>();

    // ingredient_symnonym(재료동의어) 연관관계
    @OneToMany(mappedBy = "ingredient")
    private List<IngredientSynonym> ingredientSynonymList = new ArrayList<>();
}
