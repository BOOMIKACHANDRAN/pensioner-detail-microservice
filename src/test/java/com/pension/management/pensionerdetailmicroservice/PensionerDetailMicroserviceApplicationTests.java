package com.pension.management.pensionerdetailmicroservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pension.management.pensionerdetailmicroservice.dao.PensionerDetailDao;
import com.pension.management.pensionerdetailmicroservice.model.PensionerDetail;

@SpringBootTest
class PensionerDetailMicroserviceApplicationTests {

	/*@Test
	void contextLoads() {
	}*/
	
	@Autowired
	private PensionerDetailDao pensionerDetail;
	
	@Test
	public void getPensionerDetailsTest() {
		pensionerDetail.loadPensionerDetails();
		PensionerDetail pensioner = pensionerDetail.getPensionerDetail(pensionerDetail.loadPensionerDetails(), 152233445566L);
		
		assertEquals("Chandran", pensioner.getPensionerName());
	}
	
	@Test
	public void getPensionerDetailsNullTest() {
		pensionerDetail.loadPensionerDetails();
		PensionerDetail pensioner = pensionerDetail.getPensionerDetail(pensionerDetail.loadPensionerDetails(), 15223445566L);
		
		assertNull(pensioner);
	}

	
}
