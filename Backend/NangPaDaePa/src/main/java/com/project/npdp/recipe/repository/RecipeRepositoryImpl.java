package com.project.npdp.recipe.repository;

import com.project.npdp.recipe.entity.QRecipe;
import com.project.npdp.recipe.entity.Recipe;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class RecipeRepositoryImpl implements RecipeRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    @Override
    public List<Recipe> findRecipeByContent(String content) {
        QRecipe recipe = QRecipe.recipe;

        List<Recipe> wantRecipeList = queryFactory
                .selectFrom(recipe)
                .where(recipe.name.contains(content))
                .fetch();

        return wantRecipeList;
    }

}
