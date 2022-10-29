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
@Table(name = "dc_childrens")
public class KidsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="children_id")
	private int childrenId;
	@Column(name="case_no")
	private Long caseNo;
	@Column(name="kid_name")
	private String kidName;
	private int age;
	@Column(name="kid_ssn")
	private int kidSsn;
}
