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
    public List<Ingredient> findMemberIngredient(Long memberId) {
        QMember member = QMember.member;
        QRefregirator refregirator = QRefregirator.refregirator;

        return queryFactory
                .select(ingredient)
                .from(ingredient)
                .join(ingredient.refregiratorList, refregirator)
                .join(refregirator.member, member)
                .where(member.id.eq(memberId))
                .fetch();
    }

    public List<Ingredient> findIngredientsByMemberIdWithFetchJoin(Long memberId) {
        QMember member = QMember.member;
        QRefregirator refregirator = QRefregirator.refregirator;
        QIngredient ingredient = QIngredient.ingredient;

        return queryFactory
                .selectFrom(refregirator)
                .join(refregirator.member, member).fetchJoin()
                .join(refregirator.ingredient, ingredient).fetchJoin()
                .where(member.id.eq(memberId))
                .select(ingredient)
                .fetch();
    }

}
