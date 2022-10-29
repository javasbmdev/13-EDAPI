package com.mp.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "eligibility_details")
public class EdEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ed_trace_id")
	private Long edTraceId;
	@Column(name="case_no")
	private Long caseNo;
	@Column(name = "holder_name")
	private String holderName;
	private Long ssn;
	@Column(name="plan_name")
	private String planName;
	@Column(name="plan_status")
	private String planStatus;
	@Column(name="plan_start_date")
	private LocalDate planStartDate;
	@Column(name="plan_end_date")
	private LocalDate PlanEndDate;
	@Column(name="benefit_amount")
	private double benefitAmount;
	@Column(name="denial_reason")
	private String denialReason;

}
