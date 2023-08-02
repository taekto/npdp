package com.project.npdp.recipe.repository;

import com.project.npdp.recipe.entity.Recipe;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

import static com.project.npdp.recipe.entity.QRecipe.recipe;


@RequiredArgsConstructor
public class RecipeRepositoryImpl implements RecipeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Recipe> findRecipeByContent(String content) {
        List<Recipe> wantRecipeList = queryFactory
                .selectFrom(recipe)
                .where(recipe.name.contains(content))
                .fetch();

        return wantRecipeList;
    }

}
