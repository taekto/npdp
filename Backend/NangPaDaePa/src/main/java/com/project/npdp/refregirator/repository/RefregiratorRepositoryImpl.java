package com.project.npdp.refregirator.repository;

import com.project.npdp.food.entity.Ingredient;
import com.project.npdp.food.entity.QIngredient;
import com.project.npdp.member.entity.QMember;
import com.project.npdp.refregirator.entity.QRefregirator;
import com.project.npdp.refregirator.entity.Refregirator;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

import static com.project.npdp.food.entity.QIngredient.ingredient;
import static com.project.npdp.refregirator.entity.QRefregirator.*;


@RequiredArgsConstructor
public class RefregiratorRepositoryImpl implements RefregiratorRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Refregirator> findMemberRefregirator(Long memberId) {
        List<Refregirator> refrigerators = queryFactory
                .selectFrom(refregirator)
                .where(refregirator.member.id.eq(memberId))
                .fetch();
        return refrigerators;
    }

}
