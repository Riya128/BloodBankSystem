package com.bloodbankservice.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.bloodbankservice.shared.BloodBankDto;

public interface BloodBankService extends UserDetailsService {
	BloodBankDto createUser(BloodBankDto userDetails);
	BloodBankDto getUserDetailsByEmail(String email);
	BloodBankDto getUserDetailsByUserId(String userId);
	BloodBankDto updateUser(String userId, BloodBankDto user);
	List<BloodBankDto> getUsers();
}
