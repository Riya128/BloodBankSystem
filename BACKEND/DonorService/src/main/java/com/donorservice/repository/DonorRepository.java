package com.donorservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.donorservice.data.DonorEntity;

public interface DonorRepository extends PagingAndSortingRepository<DonorEntity, Long> {

	DonorEntity findByEmail(String email);
	DonorEntity findByUserId(String userId);
}
