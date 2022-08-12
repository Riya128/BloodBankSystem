package com.adminservice.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adminservice.dto.AdminDto;

import com.adminservice.model.ResponseModel;
import com.adminservice.services.AdminService;

@RequestMapping("/admin")
@RestController

public class AdminController {

	@Autowired
	AdminService adminService;

	Logger logger = LoggerFactory.getLogger(AdminController.class);

	@PostMapping("/register")
	public ResponseEntity<ResponseModel> createUser(@RequestBody Object user) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		AdminDto userDto = modelMapper.map(user, AdminDto.class);
		AdminDto createdUser = adminService.createUser(userDto);
		ResponseModel returnVal = modelMapper.map(createdUser, ResponseModel.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(returnVal);
	}

}
