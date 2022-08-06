package com.pension.management.pensionerdetailmicroservice.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class PensionerDetail {
	
	@JsonIgnore
	private Long aadhaarNumber;

	private String pensionerName;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date dateOfBirth;
	
	private String panNumber;
	
	private Double salaryEarned;
	
	private Double allowances;
	
	private String pensiontype;
	
	private Bank bankDetail;
	

	public PensionerDetail() {
		super();
	}
	
	public PensionerDetail(Long aadhaarNumber, String pensionerName, Date dateOfBirth, String panNumber,
			Double salaryEarned, Double allowances, String pensiontype, Bank bankDetail) {
		super();
		this.aadhaarNumber = aadhaarNumber;
		this.pensionerName = pensionerName;
		this.dateOfBirth = dateOfBirth;
		this.panNumber = panNumber;
		this.salaryEarned = salaryEarned;
		this.allowances = allowances;
		this.pensiontype = pensiontype;
		this.bankDetail = bankDetail;
	}

	public Long getAadhaarNumber() {
		return aadhaarNumber;
	}

	public void setAadhaarNumber(Long aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}

	public String getPensionerName() {
		return pensionerName;
	}

	public void setPensionerName(String pensionerName) {
		this.pensionerName = pensionerName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public Double getSalaryEarned() {
		return salaryEarned;
	}

	public void setSalaryEarned(Double salaryEarned) {
		this.salaryEarned = salaryEarned;
	}

	public Double getAllowances() {
		return allowances;
	}

	public void setAllowances(Double allowances) {
		this.allowances = allowances;
	}

	public String getPensiontype() {
		return pensiontype;
	}

	public void setPensiontype(String pensiontype) {
		this.pensiontype = pensiontype;
	}


	public Bank getBankDetail() {
		return bankDetail;
	}


	public void setBankDetail(Bank bankDetail) {
		this.bankDetail = bankDetail;
	}

}
