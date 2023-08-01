package com.project.npdp.refregirator.repository;

import com.project.npdp.food.entity.Ingredient;
import com.project.npdp.refregirator.entity.Refregirator;

import java.util.List;

public interface RefregiratorRepositoryCustom {

    public List<Ingredient> findMemberIngredient(Long memberId);

}
