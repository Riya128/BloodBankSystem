package com.userservice.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.userservice.shared.UserDto;

public interface UserService extends UserDetailsService {
	UserDto createUser(UserDto userDetails);
	UserDto getUserDetailsByEmail(String email);
	UserDto getUserDetailsByUserId(String userId);
	UserDto updateUser(String userId, UserDto user);
	List<UserDto> getUsers();
}
