package com.adminservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.adminservice.entity.AdminEntity;

public interface AdminRepository extends PagingAndSortingRepository<AdminEntity, Long> {

	AdminEntity findByEmail(String email);
	
}
