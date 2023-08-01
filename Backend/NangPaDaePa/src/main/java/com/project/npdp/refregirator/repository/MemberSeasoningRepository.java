package com.project.npdp.refregirator.repository;

import com.project.npdp.refregirator.entity.MemberSeasoning;
import com.project.npdp.refregirator.entity.Refregirator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberSeasoningRepository extends JpaRepository<MemberSeasoning, Long>, MemberSeasoningRepositoryCustom {
}
