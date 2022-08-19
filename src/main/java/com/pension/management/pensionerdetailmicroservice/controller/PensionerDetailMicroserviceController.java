package com.pension.management.pensionerdetailmicroservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.pension.management.pensionerdetailmicroservice.dao.PensionerDetailDao;
import com.pension.management.pensionerdetailmicroservice.exception.AadhaarNumberNotFoundException;
import com.pension.management.pensionerdetailmicroservice.exception.TokenExpiredException;
import com.pension.management.pensionerdetailmicroservice.model.PensionerDetail;

@RestController
public class PensionerDetailMicroserviceController {

	private Logger LOGGER = LoggerFactory.getLogger(PensionerDetailMicroserviceController.class);

	@Autowired
	private PensionerDetailDao pensionerDetailService;
	
	@GetMapping("/PensionerDetailByAadhaar/{aadhaarNumber}")
	public PensionerDetail getPensionerDetail(@RequestHeader("Authorization") String token, @PathVariable Long aadhaarNumber) {
		LOGGER.info("Validating Token");
		if(pensionerDetailService.validateToken(token)) {
			LOGGER.info("Token is valid");
			LOGGER.info("Loading Pensioner Details");
			List<PensionerDetail> pensionerDetails = pensionerDetailService.loadPensionerDetails();
			LOGGER.info("Loaded Pensioner Details");
			
			PensionerDetail pensioner = pensionerDetailService.getPensionerDetail(pensionerDetails, aadhaarNumber);
			
			if(pensioner!= null){
				LOGGER.info("Inside Controller : Pensioner Detail found with this Aadhar Number");
				return pensioner;
			}
			else {
				LOGGER.info("Inside Controller : No Pensioner found with this Aadhar Number");
				return null;
			}
		}
		else {
			LOGGER.info("Token is Invalid");
			throw new TokenExpiredException("Token Expired");
		}
		
	}
}
