package com.mp.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mp.entities.CaseEntity;
@Repository
public interface CaseRepository extends JpaRepository<CaseEntity, Serializable>{
	public CaseEntity  findByApplicationId(Integer applicationId);
}
