package com.project.npdp.member.repository;

import com.project.npdp.member.entity.MemberDislikeIngredient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberDislikeRepositoryImpl implements MemberDislikeRepositoryCustom{

    private final EntityManager em;

    @Override
    public List<MemberDislikeIngredient> selectDislikeIngredient(Long memberId) {

        String jpql = "SELECT mdi FROM MemberDislikeIngredient mdi WHERE mdi.member.id = :memberId";

        return em.createQuery(jpql, MemberDislikeIngredient.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }
}
