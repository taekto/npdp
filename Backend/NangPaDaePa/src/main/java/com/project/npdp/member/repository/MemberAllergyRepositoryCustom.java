package com.project.npdp.member.repository;

import com.project.npdp.member.dto.response.AllergyResponseDto;
import com.project.npdp.member.dto.response.MemberAllergyResponseDto;
import com.project.npdp.member.entity.MemberAllergy;

import java.util.List;

public interface MemberAllergyRepositoryCustom {

    public List<MemberAllergy> selectMemberAllergy(Long memberId);

    public void deleteMemberAllergy(Long memberId);

    public List<AllergyResponseDto> findAllergyAll();
}
