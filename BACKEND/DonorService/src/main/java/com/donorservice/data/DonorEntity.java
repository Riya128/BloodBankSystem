package com.donorservice.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "donor")
@Data
public class DonorEntity implements Serializable {

	private static final long serialVersionUID = 7062902855285521641L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false, unique = true)
	private String userId;

	@Column(nullable = false, length = 50)
	private String name;
	@Column(nullable = false, length = 120, unique = true)
	private String email;

	@Column(nullable = false)
	private String bloodGroup;
	@Column(nullable = false)
	private String city;
	@Column(nullable = false)
	private String address;
	@Column(nullable = false)
	private int age;
	@Column(nullable = false, length = 10)
	private String phoneNumber;

	
}
