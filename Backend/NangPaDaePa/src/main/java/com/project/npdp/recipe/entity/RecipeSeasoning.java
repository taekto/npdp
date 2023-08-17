package com.project.npdp.recipe.entity;

import com.project.npdp.food.entity.Seasoning;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Getter
public class RecipeSeasoning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_seasoning_id")
    private Long id;

    private String amount;

    private String unit;

    private String etc;

    // recipe(레시피) 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    // seasoning(양념) 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seasoning_id")
    private Seasoning seasoning;
}
