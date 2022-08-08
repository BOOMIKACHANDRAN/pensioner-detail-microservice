package com.pension.management.pensionerdetailmicroservice.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.pension.management.pensionerdetailmicroservice.dao.PensionerDetailDao;
import com.pension.management.pensionerdetailmicroservice.model.Bank;
import com.pension.management.pensionerdetailmicroservice.model.PensionerDetail;
import com.pension.management.pensionerdetailmicroservice.proxy.AuthorizationProxy;

@Component
public class PensionerDetailDaoImpl implements PensionerDetailDao {

	private static Logger LOGGER = LoggerFactory.getLogger(PensionerDetailDaoImpl.class);

	@Autowired
	private AuthorizationProxy authorizationProxy;

	/*
	 * Method to validate token
	 */
	@Override
	public boolean validateToken(String token) {
		LOGGER.info("Inside validate token - Validating token");
		return authorizationProxy.validateToken(token);
	}

	/**
	 * This method reads data from CSV file
	 *
	 */
	@Override
	public List<PensionerDetail> loadPensionerDetails() {

		LOGGER.info("Inside loadPensionerDetails");
		List<PensionerDetail> pensionersList = new ArrayList<>();
		pensionersList.clear();
		String delimiter = ",";
		String line;
		String[] pensionerString;
		PensionerDetail pensioner;
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(this.getClass().getResourceAsStream("/pensionerDetails.csv")));
			while ((line = br.readLine()) != null) {
				pensionerString = line.split(delimiter);
				pensioner = new PensionerDetail(Long.parseLong(pensionerString[0]), pensionerString[1],
						new SimpleDateFormat("dd-MM-yyyy").parse(pensionerString[2]), pensionerString[3],
						Double.parseDouble(pensionerString[4]), Double.parseDouble(pensionerString[5]),
						pensionerString[6],
						new Bank(pensionerString[7], Long.parseLong(pensionerString[8]), pensionerString[9]));

				pensionersList.add(pensioner);
			}
		} catch (NumberFormatException | IOException | ParseException e) {
			LOGGER.info("Exception in loading data from CSV file");
		}

		return pensionersList;
	}

	/**
	 * This method gets pensioner detail for the specific Aadhaar Number
	 *
	 */
	@Override
	public PensionerDetail getPensionerDetail(List<PensionerDetail> pensionerDetails, Long aadhaarNumber) {
		LOGGER.info("Inside getPensionerDetail ");
		for (PensionerDetail pensioner : pensionerDetails) {
			if ((pensioner.getAadhaarNumber()).equals(aadhaarNumber)) {
				LOGGER.info("Pensioner Found with Aadhaar Number : " + aadhaarNumber);
				return pensioner;
			}
		}
		LOGGER.info("Pensioner Not Found with Aadhaar Number : " + aadhaarNumber);
		return null;
	}

}
