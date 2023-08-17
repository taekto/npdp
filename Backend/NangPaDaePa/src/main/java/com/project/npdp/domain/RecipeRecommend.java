package com.project.npdp.domain;

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
public class RecipeRecommend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_recommend_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // Many RecipeRelation instances can relate to one recipeOwn
    @JoinColumn(name = "recipe_own_id")
    private Recipe recipeOwn;

    @ManyToOne(fetch = FetchType.LAZY) // Many RecipeRelation instances can relate to one recipeSlave
    @JoinColumn(name = "recipe_slave_id")
    private Recipe recipeSlave;

    private Double similarity;

}
