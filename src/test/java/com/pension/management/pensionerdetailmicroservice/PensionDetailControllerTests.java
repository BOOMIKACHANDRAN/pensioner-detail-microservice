package com.pension.management.pensionerdetailmicroservice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.pension.management.pensionerdetailmicroservice.controller.PensionerDetailMicroserviceController;
import com.pension.management.pensionerdetailmicroservice.dao.PensionerDetailDao;
import com.pension.management.pensionerdetailmicroservice.model.Bank;
import com.pension.management.pensionerdetailmicroservice.model.PensionerDetail;

@AutoConfigureMockMvc
@SpringBootTest
public class PensionDetailControllerTests {

	@Mock
	private PensionerDetailDao pensionerDetailService;

	@InjectMocks
	PensionerDetailMicroserviceController pensionerDetailMicroserviceController;

	@Autowired
	MockMvc mockMvc;

	@BeforeEach
    void setup()
    {
        mockMvc=MockMvcBuilders.standaloneSetup(pensionerDetailMicroserviceController).build();        
    }
	
	@Test
	void getPensionerDetailTest() throws Exception {
		
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJCb29taWthIiwiZXhwIjoxNjYwOTAyMjE5LCJpYXQiOjE2NjA5MDA0MTl9.SNGHWNLeCRxwPvT6fjIT2r6Foc9ovRUdbmfp6oxxqCE";
		
		PensionerDetail pensioner = new PensionerDetail(123456789012L, "Boomika",new Date(), "ADBC345F", 15000.0, 10000.0,
				"family", new Bank("HDFC", 123456L, "private"));
		
		List<PensionerDetail> pensionerDetails = new ArrayList<>();
		pensionerDetails.add(pensioner);
		
		Mockito.when(pensionerDetailService.validateToken(token)).thenReturn(true);
		Mockito.when(pensionerDetailService.loadPensionerDetails()).thenReturn(pensionerDetails);
		Mockito.when(pensionerDetailService.getPensionerDetail(pensionerDetails, 123456789012L)).thenReturn(pensioner);
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/PensionerDetailByAadhaar/{aadhaarNumber}",123456789012L).header("Authorization", token))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
        .andDo(MockMvcResultHandlers.print());
	}

}
