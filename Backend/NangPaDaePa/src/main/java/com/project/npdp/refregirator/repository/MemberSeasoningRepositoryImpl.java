package com.project.npdp.refregirator.repository;

import com.project.npdp.food.entity.QIngredient;
import com.project.npdp.food.entity.QSeasoning;
import com.project.npdp.member.entity.QMember;
import com.project.npdp.refregirator.dto.response.MemberIngredientFindResponseDto;
import com.project.npdp.refregirator.dto.response.MemberSeasoningFindResponseDto;
import com.project.npdp.refregirator.entity.MemberSeasoning;
import com.project.npdp.refregirator.entity.QMemberSeasoning;
import com.project.npdp.refregirator.entity.QRefregirator;
import com.project.npdp.refregirator.entity.Refregirator;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class MemberSeasoningRepositoryImpl implements MemberSeasoningRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    
    // 회원 양념 조회
    @Override
    public List<MemberSeasoningFindResponseDto> findMemberSeasoning(Long memberId) {
        QMemberSeasoning memberSeasoning = QMemberSeasoning.memberSeasoning;
        QSeasoning seasoning = QSeasoning.seasoning;

        List<Tuple> results = queryFactory
                .select(memberSeasoning.id , seasoning.kor, memberSeasoning.storage, memberSeasoning.startDate, memberSeasoning.expiredDate)
                .from(memberSeasoning)
                .join(memberSeasoning.seasoning, seasoning)
                .where(memberSeasoning.member.id.eq(memberId))
                .fetch();

        return results.stream()
                .map(tuple -> new MemberSeasoningFindResponseDto(
                        tuple.get(memberSeasoning.id),
                        tuple.get(seasoning.kor),
                        tuple.get(memberSeasoning.storage),
                        tuple.get(memberSeasoning.startDate),
                        tuple.get(memberSeasoning.expiredDate)))
                .collect(Collectors.toList());
    }

}
