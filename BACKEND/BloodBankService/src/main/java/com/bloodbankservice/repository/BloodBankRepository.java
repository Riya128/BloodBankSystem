package com.bloodbankservice.repository;


import org.springframework.data.repository.PagingAndSortingRepository;

import com.bloodbankservice.data.BloodBankEntity;

public interface BloodBankRepository extends PagingAndSortingRepository<BloodBankEntity, Long> {

	BloodBankEntity findByEmail(String email);
	BloodBankEntity findByUserId(String userId);
}
