package com.project.npdp.member.repository;

import com.project.npdp.domain.MemberRecommend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<MemberRecommend, Long> {
}
