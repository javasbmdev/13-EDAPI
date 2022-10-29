package com.mp.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mp.entities.IncomeEnitty;

@Repository
public interface IncomeRepository extends JpaRepository<IncomeEnitty, Serializable> {
	public IncomeEnitty findByCaseNo(Long caseNo);
	
//	@Query("monthlySalary from Income inc where inc.caseNo=:caseNo")
//	double findIncome(@Param("caseNo") Long caseNo);
//	double monthlyIncome = 299;
//	List<IncomeEnitty> findByCaseNoAndMonthlyIncomeGreaterThan(Long caseNo,Double monthlyIncome);
//	

}
