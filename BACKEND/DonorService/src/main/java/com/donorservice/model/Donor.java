package com.donorservice.model;

import lombok.Data;

//fields we will receive from the user
@Data
public class Donor {
	private String name;
	private String email;
	private String bloodGroup;
	private String phoneNumber;
	private String city;
	private String address;
	private int age;

	
}
