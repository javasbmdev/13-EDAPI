package com.mp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "dc_income")
public class IncomeEnitty {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "income_id")
	private Integer incomeId;
	@Column(name = "case_no")
	private Long caseNo;
	@Column(name = "monthlySalary")
	private double monthlySalary;
	@Column(name = "rent_income")
	private double rentIncome;
	@Column(name = "property_income")
	private double propertyIncome;

}
