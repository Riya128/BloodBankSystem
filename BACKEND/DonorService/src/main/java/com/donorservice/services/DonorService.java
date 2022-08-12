package com.donorservice.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.donorservice.shared.DonorDto;

public interface DonorService{
	DonorDto createUser(DonorDto userDetails);

	List<DonorDto> getUsers();
	
	ResponseEntity<HttpStatus> deleteDonor(String userId);
}
