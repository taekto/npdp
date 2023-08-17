package com.project.npdp.member.repository;

import com.project.npdp.domain.Allergy;
import com.project.npdp.domain.QAllergy;
import com.project.npdp.member.dto.response.AllergyResponseDto;
import com.project.npdp.member.dto.response.MemberAllergyResponseDto;
import com.project.npdp.member.entity.MemberAllergy;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static com.project.npdp.domain.QAllergy.allergy;


@RequiredArgsConstructor
public class MemberAllergyRepositoryImpl implements MemberAllergyRepositoryCustom{

    private final EntityManager em;

    private final JPAQueryFactory queryFactory;

    @Override
    public List<MemberAllergy> selectMemberAllergy(Long memberId){
        // select ma.member_id, a.name from member_allergy ma join allergy a using(allergy_id) where ma.member_id = memberId;
        String jpql = "SELECT ma FROM MemberAllergy ma WHERE ma.member.id = :memberId";

        return em.createQuery(jpql, MemberAllergy.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    @Override
    public void deleteMemberAllergy(Long memberId) {

        String jpql = "DELETE FROM MemberAllergy ma WHERE ma.member.id = :memberId";

        em.createQuery(jpql)
                .setParameter("memberId", memberId)
                .executeUpdate();
    }

    @Override
    public List<AllergyResponseDto> findAllergyAll() {
        List<Allergy> allergies = queryFactory.selectFrom(allergy).fetch();

        return allergies.stream().map(
                allergyEntity -> AllergyResponseDto.builder()
                        .allergyId(allergyEntity.getId())
                        .allergyName(allergyEntity.getName()).build()
        ).collect(Collectors.toList());
    }
}
