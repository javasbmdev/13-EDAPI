package com.mp.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import org.springframework.web.bind.annotation.RestController;



import com.mp.bindings.EdResponse;
import com.mp.service.EdService;

@RestController
public class EdRestController {
	@Autowired
	private EdService edService;
	@GetMapping("/{caseNo}/eligibility")
	public  EdResponse determineEligibility(@PathVariable Long caseNo) {
		EdResponse edResponse =edService .checkEligibility(caseNo);
		System.out.println("kldjfld");
		return edResponse;
		
		 
	}
}
