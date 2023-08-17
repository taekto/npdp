package com.project.npdp.food.entity;

import com.project.npdp.refregirator.entity.MemberSeasoning;
import com.project.npdp.recipe.entity.RecipeSeasoning;
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
public class Seasoning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seasoning_id")
    private Long id;

    private String code;

    private String kor;

    private String eng;

    private String def;

    private Long level;

    private String upperClass;

    private String superUpperClass;

    private String etc;

    // member_seasoning(양념칸) 연관관계
    @OneToMany(mappedBy = "seasoning")
    private List<MemberSeasoning> memberSeasoningList = new ArrayList<>();

    // recipe_seasoning(레시피포함양념) 연관관계
    @OneToMany(mappedBy = "seasoning")
    private List<RecipeSeasoning> recipeIngredientList = new ArrayList<>();

    // seasoning_symnonym(양념동의어) 연관관계
    @OneToMany(mappedBy = "seasoning")
    private List<SeasoningSynonym> seasoningSynonymList = new ArrayList<>();
}
