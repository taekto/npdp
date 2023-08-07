package com.project.npdp.refregirator.repository;

import com.project.npdp.food.entity.Ingredient;
import com.project.npdp.refregirator.dto.response.MemberIngredientFindResponseDto;
import com.project.npdp.refregirator.entity.Refregirator;

import java.util.List;

public interface RefregiratorRepositoryCustom {

    public List<MemberIngredientFindResponseDto> findMemberIngredient(Long memberId);

}
