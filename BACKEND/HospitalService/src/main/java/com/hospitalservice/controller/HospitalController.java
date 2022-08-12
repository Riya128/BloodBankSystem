package com.hospitalservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hospitalservice.model.ResponseModel;
import com.hospitalservice.services.HospitalService;
import com.hospitalservice.shared.HospitalDto;


import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/hospital")
public class HospitalController {

	Logger logger = LoggerFactory.getLogger(HospitalController.class);
	
	@Autowired
	private HospitalService userService;
	
	//@GetMapping(path = "/{id}")
	@GetMapping("/{id}")
	public ResponseModel getUser(@PathVariable String id) {
		ResponseModel returnValue = new ResponseModel();
		logger.debug("calling Service class method getUserDetailsByUserId()");
		HospitalDto userDto = userService.getUserDetailsByUserId(id);
		BeanUtils.copyProperties(userDto, returnValue);
		logger.info("Everything went well, Here are the details asked!");
		//ModelMapper modelMapper = new ModelMapper();
		//returnValue = modelMapper.map(userDto, UserResponseModel.class);
		return returnValue;
	}
	
	@PutMapping("/{id}")
	public ResponseModel updateUser(@PathVariable String id, @RequestBody ResponseModel responseModel) {
		ResponseModel returnValue = new ResponseModel();
		logger.debug("calling Service class method getUserDetailsByUserId()");
		HospitalDto userDto = userService.getUserDetailsByUserId(id);
		BeanUtils.copyProperties(responseModel, userDto);
		logger.debug("Service class method updateUser() called");
		HospitalDto updateUser = userService.updateUser(id,userDto);
		BeanUtils.copyProperties( updateUser,returnValue);
		//ModelMapper modelMapper = new ModelMapper();
		//returnValue = modelMapper.map(userDto, UserResponseModel.class);
		logger.info("Details Updated successfully!!");
		return returnValue;
	}
	
	@PostMapping("/register")
	public ResponseEntity<ResponseModel> createUser( @RequestBody Object user) {
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		HospitalDto userDto=modelMapper.map(user, HospitalDto.class);
		logger.debug("Stored detials.\nCalling createUser() from service class");
		HospitalDto createdUser=userService.createUser(userDto);
		ResponseModel returnVal=modelMapper.map(createdUser, ResponseModel.class);
		logger.info("Hospital Account Created Successfully!!");
		return ResponseEntity.status(HttpStatus.CREATED).body(returnVal);
	}
	
	@GetMapping("/allhospitals")
	public List<ResponseModel> getUsers() {
		List<ResponseModel> returnValue = new ArrayList<>();
		logger.debug("Service class method getUsers() called, retrieving hospital List");
		List<HospitalDto> users = userService.getUsers();
		
		for (HospitalDto userDto : users) {
			ResponseModel userModel = new ResponseModel();
			BeanUtils.copyProperties(userDto, userModel);
			returnValue.add(userModel);
		}
		
		logger.info("Hospital List Provided!!");
		return returnValue;
	}
}

