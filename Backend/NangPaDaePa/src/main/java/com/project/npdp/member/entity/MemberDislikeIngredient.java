package com.project.npdp.member.entity;

import com.project.npdp.food.entity.Ingredient;
import com.project.npdp.recipe.entity.Recipe;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Getter
public class MemberDislikeIngredient {

    @Id
    @GeneratedValue
    @Column(name = "member_dislike_ingredient_id")
    private Long id;

    // recipe(레시피) 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    // ingredient(재료) 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;
    
    // member(회원) 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
