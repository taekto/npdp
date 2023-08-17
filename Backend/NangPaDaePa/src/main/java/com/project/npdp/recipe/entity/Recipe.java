package com.project.npdp.recipe.entity;

import com.project.npdp.domain.Calendar;
import com.project.npdp.domain.MemberRecommend;
import com.project.npdp.domain.RecipeRecommend;
import com.project.npdp.member.entity.MemberRecipeLatest;
import com.project.npdp.member.entity.MemberRecipeLike;
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
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private Long id;

    private String name;

    private Long way;

    private Double weight;

    private Double calorie;

    private Double carbohydrate;

    private Double protein;

    private Double fat;

    private Double salt;

    private String imgSmall;

    private String imgBig;

    private String category;

    private Long dish;

    // calendar(캘린더) 연관관계
    @OneToMany(mappedBy = "recipe")
    private List<Calendar> calendarList = new ArrayList<>();

    // recipe_utensil(조리장비) 연관관계
    @OneToMany(mappedBy = "recipe")
    private List<RecipeUtensil> recipeUtensilList = new ArrayList<>();

    // recipe_ingredient(레시피포함재료) 연관관계
    @OneToMany(mappedBy = "recipe")
    private List<RecipeIngredient> recipeIngredientList = new ArrayList<>();

    // recipe_seasoning(레시피포함양념) 연관관계
    @OneToMany(mappedBy = "recipe")
    private List<RecipeSeasoning> recipeSeasoningList = new ArrayList<>();

    // recipe_procedure(과정) 연관관계
    @OneToMany(mappedBy = "recipe")
    private List<RecipeSequence> recipeSequenceList = new ArrayList<>();

    // member_recipe_like(과정) 연관관계
    @OneToMany(mappedBy = "recipe")
    private List<MemberRecipeLike> memberRecipeLikeList = new ArrayList<>();

    // member_recipe_like(과정) 연관관계
    @OneToMany(mappedBy = "recipe")
    private List<MemberRecipeLatest> memberRecipeLatestList = new ArrayList<>();

    // member_recommend(회원추천) 연관관계
    @OneToMany(mappedBy = "member")
    private List<MemberRecommend> memberRecommendList = new ArrayList<>();

    @OneToMany(mappedBy = "recipeOwn")
    private List<RecipeRecommend> recipeRecommendList2 = new ArrayList<>();

    @OneToMany(mappedBy = "recipeSlave")
    private List<RecipeRecommend> recipeRecommendList1 = new ArrayList<>();

}
