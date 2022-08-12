package com.bloodbankservice.model;

import lombok.Data;

//fields we will receive from the user
@Data
public class BloodBank {
	private String name;
	private String email;
	private String password;
	private String phoneNumber;
	private String city;
	private String address;

	
	
	
}
