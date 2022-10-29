package com.mp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "co_triggers")
@Entity
@Data
public class CorrespondenceEntity {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	@Column(name = "co_trigger_id")
	private Long coTriggerId;
	@Column(name="case_no")
	private Long caseNo;
	@Column(name="co_pdf")
	private byte[] coPdf;
	@Column(name="trigger_status")
	private String triggerStatus;
}
