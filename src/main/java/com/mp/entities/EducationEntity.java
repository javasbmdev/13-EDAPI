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
@Table(name = "dc_education")
public class EducationEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="education_id")
	private Integer educationId;
	@Column(name="case_no")
	private Long caseNo;
	@Column(name="highest_degree")
	private String highestDegree;
	@Column(name="university_name")
	private String universityName;
	@Column(name="year_of_passing")
	private LocalDate yearOfPassing;
}
