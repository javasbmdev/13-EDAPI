package com.mp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "dc_cases")
@Data
public class CaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "case_no")
	private Long caseNo;
	@Column(name = "application_id")
	private Integer applicationId;
	@Column(name="plan_id")
	private Integer planId;

}
