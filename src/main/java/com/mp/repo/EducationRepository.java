package com.mp.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mp.entities.EducationEntity;
@Repository
public interface EducationRepository extends JpaRepository<EducationEntity, Serializable>{
	public EducationEntity findByCaseNo(Long caseNo);

}
