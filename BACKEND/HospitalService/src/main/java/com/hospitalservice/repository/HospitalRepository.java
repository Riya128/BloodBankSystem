package com.hospitalservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.hospitalservice.data.HospitalEntity;

public interface HospitalRepository extends PagingAndSortingRepository<HospitalEntity, Long> {

	HospitalEntity findByEmail(String email);
	HospitalEntity findByUserId(String userId);
}
