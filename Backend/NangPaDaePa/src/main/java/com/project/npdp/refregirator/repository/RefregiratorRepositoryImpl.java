package com.project.npdp.refregirator.repository;

import com.project.npdp.food.entity.Ingredient;
import com.project.npdp.food.entity.QIngredient;
import com.project.npdp.member.entity.QMember;
import com.project.npdp.refregirator.dto.response.MemberIngredientFindResponseDto;
import com.project.npdp.refregirator.entity.QRefregirator;
import com.project.npdp.refregirator.entity.Refregirator;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static com.project.npdp.food.entity.QIngredient.ingredient;
import static com.project.npdp.refregirator.entity.QRefregirator.*;


@RequiredArgsConstructor
public class RefregiratorRepositoryImpl implements RefregiratorRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    
    // 회원 재료 조회
    @Override
    public List<MemberIngredientFindResponseDto> findMemberIngredient(Long memberId) {
        QRefregirator refregirator = QRefregirator.refregirator;
        QIngredient ingredient = QIngredient.ingredient;

        List<Tuple> results = queryFactory
                .select(refregirator.id,ingredient.kor, refregirator.amount, refregirator.unit, refregirator.startDate, refregirator.expiredDate, refregirator.storage)
                .from(refregirator)
                .join(refregirator.ingredient, ingredient)
                .where(refregirator.member.id.eq(memberId))
                .fetch();

        return results.stream()
                .map(tuple -> new MemberIngredientFindResponseDto(
                        tuple.get(refregirator.id),
                        tuple.get(ingredient.kor),
                        tuple.get(refregirator.amount),
                        tuple.get(refregirator.unit),
                        tuple.get(refregirator.startDate),
                        tuple.get(refregirator.expiredDate),
                        tuple.get(refregirator.storage)))
                .collect(Collectors.toList());
    }

}
