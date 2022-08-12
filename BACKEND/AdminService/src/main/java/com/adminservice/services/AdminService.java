package com.adminservice.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.adminservice.dto.AdminDto;


public interface AdminService extends UserDetailsService {
	

	AdminDto getUserDetailsByEmail(String userName);
	AdminDto createUser(AdminDto userDetails);
}
