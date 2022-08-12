package com.donorservice.model;

import lombok.Data;

//what we will send in response
@Data
public class ResponseModel {
	private String userId;
	private String name;
	private String email;
	private String bloodGroup;
	private String phoneNumber;
	private String city;
	private String address;
	private int age;

}
