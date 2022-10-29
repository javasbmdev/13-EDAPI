package com.mp.bindings;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EdResponse {
	private Long edTraceId;
	private String planName;
	private String planStatus;
	private LocalDate planStartDate;
	private LocalDate PlanEndDate;
	private double benefitAmount;
	private String denialReason;
	
}
