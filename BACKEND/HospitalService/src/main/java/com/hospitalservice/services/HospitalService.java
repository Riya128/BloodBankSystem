package com.hospitalservice.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.hospitalservice.shared.HospitalDto;

public interface HospitalService extends UserDetailsService {
	HospitalDto createUser(HospitalDto userDetails);
	HospitalDto getUserDetailsByEmail(String email);
	HospitalDto getUserDetailsByUserId(String userId);
	HospitalDto updateUser(String userId, HospitalDto user);
	List<HospitalDto> getUsers();
}
