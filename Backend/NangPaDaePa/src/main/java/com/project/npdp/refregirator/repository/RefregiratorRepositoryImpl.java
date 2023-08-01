package com.project.npdp.refregirator.repository;

import com.project.npdp.recipe.repository.RecipeRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;



public class RefregiratorRepositoryImpl implements RecipeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public RefregiratorRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

}
