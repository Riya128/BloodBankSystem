package com.cognizant.Donor.Model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Donor {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private int age;
	private long mobile;
	private String email;
	private String bloodgroup;
	private String city;
	private String Address;
	
}
