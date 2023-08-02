package com.project.npdp.refregirator.repository;

import com.project.npdp.member.entity.QMember;
import com.project.npdp.refregirator.entity.MemberSeasoning;
import com.project.npdp.refregirator.entity.QMemberSeasoning;
import com.project.npdp.refregirator.entity.QRefregirator;
import com.project.npdp.refregirator.entity.Refregirator;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.project.npdp.member.entity.QMember.member;
import static com.project.npdp.refregirator.entity.QMemberSeasoning.memberSeasoning;

@RequiredArgsConstructor
public class MemberSeasoningRepositoryImpl implements MemberSeasoningRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<MemberSeasoning> findMemberSeasoning(Long memberId) {

        List<MemberSeasoning> memberSeasoningList = queryFactory
                .selectFrom(memberSeasoning)
                .join(memberSeasoning.member, member).fetchJoin()
                .where(member.id.eq(memberId))
                .fetch();

        return memberSeasoningList;
    }

}
