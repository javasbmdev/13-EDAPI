package com.mp.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name = "citizen_apps")
@Data
public class CitizenApplicationEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "application_id")
	private Integer applicationId;
	@Column(name = "full_name")
	private String fullName;
	@Column(name = "phone_no")
	private String phoneNo;
	@Column(name = "email_id")
	private String emailId;
	private String gender;
	private Long ssn;
	@Column(name = "state_name")
	private String stateName;
	private LocalDate dob;
	@Column(name = "plan_name")
	private String planName;
	@CreationTimestamp
	@Column(name = "created_date")
	private LocalDate cratedDate;
	@UpdateTimestamp
	@Column(name = "updated_date")
	private LocalDate updatedDate;
	@Column(name = "crated_by")
	private String cratedBy;
	@Column(name = "updated_by")
	private String updatedBy;

}
