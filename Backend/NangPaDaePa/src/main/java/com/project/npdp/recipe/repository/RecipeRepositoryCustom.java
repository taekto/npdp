package com.project.npdp.recipe.repository;

import com.project.npdp.recipe.entity.Recipe;

import java.util.List;

public interface RecipeRepositoryCustom {
    public List<Recipe> findRecipeByContent(String content);


}
