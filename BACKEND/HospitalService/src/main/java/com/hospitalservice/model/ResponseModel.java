package com.hospitalservice.model;

import lombok.Data;

//what we will send in response

@Data
public class ResponseModel {
	private String userId;
	private String name;
	private String email;
	private String phoneNumber;
	private String city;
	private String address;

	
	
}
