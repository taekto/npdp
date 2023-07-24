package com.project.npdp.food.entity;

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
    @GeneratedValue
    @Column(name = "seasoning_id")
    private Long id;

    private String name;

    // member_seasoning(양념칸) 연관관계
    @OneToMany(mappedBy = "seasoning")
    private List<MemberSeasoning> memberSeasoningList = new ArrayList<>();

    // recipe_seasoning(레시피포함양념) 연관관계
    @OneToMany(mappedBy = "seasoning")
    private List<RecipeSeasoning> recipeIngredientList = new ArrayList<>();

}
