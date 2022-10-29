package com.mp.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mp.bindings.EdResponse;
import com.mp.entities.CaseEntity;
import com.mp.entities.CitizenApplicationEntity;
import com.mp.entities.CorrespondenceEntity;
import com.mp.entities.EdEntity;
import com.mp.entities.EducationEntity;
import com.mp.entities.IncomeEnitty;
import com.mp.entities.KidsEntity;
import com.mp.entities.PlanEntity;
import com.mp.repo.CaseRepository;
import com.mp.repo.CitizenRepository;
import com.mp.repo.CoRepository;
import com.mp.repo.EdRepository;
import com.mp.repo.EducationRepository;
import com.mp.repo.IncomeRepository;
import com.mp.repo.KidsRepository;
import com.mp.repo.PlanRepository;

@Service
public class EdServiceImpl implements EdService {
	@Autowired
	private CaseRepository caseRepository;
	@Autowired
	private PlanRepository planRepository;
	@Autowired
	private IncomeRepository incomeRepository;
	@Autowired
	private KidsRepository kidsRepository;
	@Autowired
	private EducationRepository educationRepository;
	@Autowired
	private EdRepository edRepository;
	@Autowired
	private CitizenRepository citizenRepository;
	@Autowired
	private CoRepository coRepository;

	private Integer applicationId;

	@Override
	public EdResponse checkEligibility(Long caseNo) {
		CorrespondenceEntity correspondenceEntity = new CorrespondenceEntity();
		EdEntity entity = validateEligibilityData(caseNo);
		entity = edRepository.save(entity);
		EdResponse edResponse = new EdResponse();
		BeanUtils.copyProperties(entity, edResponse);
		correspondenceEntity.setCaseNo(entity.getCaseNo());
		correspondenceEntity.setCoPdf(null);
		correspondenceEntity.setTriggerStatus("pending");
		coRepository.save(correspondenceEntity);
		return edResponse;
	}

	private EdEntity validateEligibilityData(Long caseNo) {
		EdEntity edEntity = new EdEntity();

		String holderName = null;
		Long ssn = null;

		IncomeEnitty incomeEnitty = incomeRepository.findByCaseNo(caseNo);
		List<KidsEntity> kidsEntity = kidsRepository.findByCaseNo(caseNo);
		Optional<CaseEntity> caseEntity = caseRepository.findById(caseNo);
		if (caseEntity.isPresent()) {
			applicationId = caseEntity.get().getApplicationId();
		}
		Optional<CitizenApplicationEntity> citizenEntity = citizenRepository.findById(applicationId);
		if(citizenEntity.isPresent()) {
			holderName = citizenEntity.get().getFullName();
			ssn = citizenEntity.get().getSsn();
		}
		
		Integer noOfKid = 0;
		boolean isAgeBelow = false;

		if (!kidsEntity.isEmpty()) {
			noOfKid = kidsEntity.size();
			isAgeBelow = kidAgeValidate(kidsEntity);
		}

		String planName = getPlanName(caseEntity);
		edEntity.setPlanName(planName);

		double salary = incomeEnitty.getMonthlySalary();

		if (planName.equalsIgnoreCase("snap")) {
			if (salary <= 300) {
				edEntity.setPlanStatus("approved");
				edEntity.setBenefitAmount(500);
			} else {
				edEntity.setPlanStatus("denied");
				edEntity.setDenialReason("Not matching snap guidline");
			}
		} else if (planName.equalsIgnoreCase("ccap")) {
			if (salary <= 300 && noOfKid > 0 && isAgeBelow) {
				edEntity.setPlanStatus("approved");
				edEntity.setBenefitAmount(700);
			} else {
				edEntity.setPlanStatus("denied");
				edEntity.setDenialReason("child age more than 16");
			}
		} else if (planName.equalsIgnoreCase("medicaid")) {
			if (salary <= 300 && incomeEnitty.getPropertyIncome() < 0) {
				edEntity.setPlanStatus("approved");
				edEntity.setBenefitAmount(800);
			} else {
				edEntity.setPlanStatus("denied");
				edEntity.setDenialReason("income is more ");
			}
		} else if (planName.equalsIgnoreCase("medicare")) {
			if (getAge(citizenEntity) > 60) {
				edEntity.setPlanStatus("approved");
				edEntity.setBenefitAmount(1000);
			} else {
				edEntity.setPlanStatus("denied");
				edEntity.setDenialReason("child age more than 16");
			}
		} else if (planName.equalsIgnoreCase("NJW")) {
			EducationEntity educationEntity = educationRepository.findByCaseNo(caseNo);
			int yearOfPassing = educationEntity.getYearOfPassing().getYear();
			int currentYear = LocalDate.now().getYear();
			if (yearOfPassing < currentYear && incomeEnitty.getMonthlySalary() <= 0) {
				edEntity.setPlanStatus("approved");
				edEntity.setBenefitAmount(1500);
			} else {
				edEntity.setPlanStatus("denied");
				edEntity.setDenialReason("data deviating from guidlines of NJW");
			}
		}
		edEntity.setCaseNo(caseNo);
		edEntity.setHolderName(holderName);
		edEntity.setSsn(ssn);
		if (edEntity.getPlanStatus().equalsIgnoreCase("approved")) {
			edEntity.setPlanStartDate(LocalDate.now());
			edEntity.setPlanEndDate(LocalDate.now().plusYears(1));
		}

		return edEntity;
	}

	private Integer getAge(Optional<CitizenApplicationEntity> entity) {
		int age = 0;
		if (entity.isPresent()) {
			return Period.between(LocalDate.now(), entity.get().getDob()).getYears();
		}
		return age;
	}

	private boolean kidAgeValidate(List<KidsEntity> kidsEntities) {
		boolean isAgeBelow = true;
		for (KidsEntity kid : kidsEntities) {
			if (kid.getAge() > 16) {
				isAgeBelow = false;
				break;
			}
		}
		return isAgeBelow;
	}

	private String getPlanName(Optional<CaseEntity> caseEntity) {
		String planName = null;
		Integer planId = 0;
		if (caseEntity.isPresent()) {
			planId = caseEntity.get().getPlanId();

			Optional<PlanEntity> planEntity = planRepository.findById(planId);
			if (planEntity.isPresent()) {
				planName = planEntity.get().getPlanName();
			}
		}
		return planName;
	}

}
