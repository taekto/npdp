package com.project.npdp.member.repository;

import com.project.npdp.member.dto.response.MemberAllergyResponseDto;
import com.project.npdp.member.entity.MemberAllergy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberAllergyRepositoryImpl implements MemberAllergyRepositoryCustom{

    private final EntityManager em;

    @Override
    public List<MemberAllergy> selectMemberAllergy(Long memberId){
        // select ma.member_id, a.name from member_allergy ma join allergy a using(allergy_id) where ma.member_id = memberId;
        String jpql = "SELECT ma FROM MemberAllergy ma WHERE ma.member.id = :memberId";

        return em.createQuery(jpql, MemberAllergy.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }
}
