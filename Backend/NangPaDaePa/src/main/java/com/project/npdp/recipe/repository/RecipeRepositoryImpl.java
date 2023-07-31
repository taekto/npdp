package com.project.npdp.recipe.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;


@RequiredArgsConstructor
public class RecipeRepositoryImpl implements RecipeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

//    public RecipeRepositoryImpl(EntityManager em) {
//        this.queryFactory = new JPAQueryFactory(em);
//    }

}
