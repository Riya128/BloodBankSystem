package com.hospitalservice.model;

import lombok.Data;

//fields we will receive from the user
@Data
public class User {
	private String name;
	private String email;
	private String password;
	private String phoneNumber;
	private String city;
	private String address;

}
