package com.project.npdp.refregirator.repository;

import com.project.npdp.refregirator.dto.response.MemberSeasoningFindResponseDto;
import com.project.npdp.refregirator.entity.MemberSeasoning;

import java.util.List;

public interface MemberSeasoningRepositoryCustom {

    public List<MemberSeasoningFindResponseDto> findMemberSeasoning(Long memberId);

}
