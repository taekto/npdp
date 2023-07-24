package com.project.npdp.utensil.entity;

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
public class Utensil {

    @Id @GeneratedValue
    @Column(name = "utensil_id")
    private Long id;

    private String name;

    private Long way;

    // member_utensil(회원보유장비) 연관관계
    @OneToMany(mappedBy = "utensil")
    private List<MemberUtensil> memberUtensilList = new ArrayList<>();

    // recipe_utensil(조리장비) 연관관계
    @OneToMany(mappedBy = "utensil")
    private List<RecipeUtensil> recipeUtensilList = new ArrayList<>();
}
