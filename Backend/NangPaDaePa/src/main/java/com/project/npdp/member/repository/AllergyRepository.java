package com.project.npdp.member.repository;

import com.project.npdp.domain.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllergyRepository extends JpaRepository<Allergy, Long> {
}
