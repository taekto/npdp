package com.project.npdp.member.repository;

import com.project.npdp.member.entity.MemberUtensil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
public class MemberUtensilRepositoryImpl implements MemberUtensilRepositoryCustom{

    private final EntityManager em;

    @Override
    public List<MemberUtensil> selectMemberUtensil(Long memberId) {

        String jpql = "SELECT mu FROM MemberUtensil mu WHERE mu.member.id = :memberId";

        return em.createQuery(jpql, MemberUtensil.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    @Override
    public void deleteMemberUtensil(Long memberId) {

        String jpql = "DELETE FROM MemberUtensil mu WHERE mu.member.id = :memberId";

        em.createQuery(jpql)
                .setParameter("memberId", memberId)
                .executeUpdate();
    }
}
