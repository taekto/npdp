package com.project.npdp.refregirator.repository;


import com.project.npdp.refregirator.entity.Refregirator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefregiratorRepository extends JpaRepository<Refregirator, Long>, RefregiratorRepositoryCustom {
}