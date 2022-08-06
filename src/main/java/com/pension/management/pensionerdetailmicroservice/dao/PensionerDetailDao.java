package com.pension.management.pensionerdetailmicroservice.dao;

import java.util.List;

import com.pension.management.pensionerdetailmicroservice.model.PensionerDetail;

public interface PensionerDetailDao {

	public boolean validateToken(String token);
	public List<PensionerDetail> loadPensionerDetails();
	public PensionerDetail getPensionerDetail(List<PensionerDetail> pensionerDetails, Long aadhaarNumber);
}
