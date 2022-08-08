package com.pension.management.pensionerdetailmicroservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="authorization-service", url="${authorization.uri}")
public interface AuthorizationProxy {

	@PostMapping("/validate-token")
	public boolean validateToken(@RequestHeader("Authorization") String token);

}
