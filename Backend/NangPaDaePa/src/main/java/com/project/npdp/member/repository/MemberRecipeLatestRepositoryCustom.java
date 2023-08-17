package com.project.npdp.member.repository;


import com.project.npdp.member.dto.response.MemberLatestResponseDto;
import com.project.npdp.member.entity.MemberRecipeLatest;
import com.project.npdp.member.entity.MemberRecipeLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRecipeLatestRepositoryCustom {

    public List<MemberLatestResponseDto> findLatestWithRecipesByMemberId(Long memberId);

}
