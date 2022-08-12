package com.bloodbankservice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bloodbankservice.model.ResponseModel;
import com.bloodbankservice.services.BloodBankService;
import com.bloodbankservice.shared.BloodBankDto;
import com.bloodbankservice.model.BloodBank;

@RestController
@RequestMapping("/bloodbank")
@CrossOrigin
public class BloodBankController {

	@Autowired
	private BloodBankService userService;
	
	Logger logger = LoggerFactory.getLogger(BloodBankController.class);

	@GetMapping("/{id}")
	public ResponseModel getUser(@PathVariable String id) {
		ResponseModel returnValue = new ResponseModel();
		logger.debug("calling Blood Bank Service class method getUserDetailsByUserId()");
		BloodBankDto userDto = userService.getUserDetailsByUserId(id);
		BeanUtils.copyProperties(userDto, returnValue);
		logger.info("Everything went well, Here are the details asked!");
		return returnValue;
	}
	@PutMapping("/{id}")
	public ResponseModel updateUser(@PathVariable String id, @RequestBody ResponseModel responseModel) {
		ResponseModel returnValue = new ResponseModel();
		logger.debug("calling Blood Bank Service class method getUserDetailsByUserId()");
		BloodBankDto userDto = userService.getUserDetailsByUserId(id);
		BeanUtils.copyProperties(responseModel, userDto);
		logger.debug("Blood Bank Service class method updateUser() called");
		BloodBankDto updateUser = userService.updateUser(id,userDto);
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
		BloodBankDto userDto=modelMapper.map(user, BloodBankDto.class);
		logger.debug("Stored detials.\nCalling createUser() from BB service class");
		BloodBankDto createdUser=userService.createUser(userDto);
		ResponseModel returnVal=modelMapper.map(createdUser, ResponseModel.class);
		logger.info("BloodBank Account Created Successfully!!");
		return ResponseEntity.status(HttpStatus.CREATED).body(returnVal);
	}
	
	@GetMapping("/allbloodbanks")
	public List<ResponseModel> getUsers() {
		List<ResponseModel> returnValue = new ArrayList<>();
		logger.debug("BB Service class method getUsers() called, retrieving Bloodbank List");
		List<BloodBankDto> users = userService.getUsers();

		for (BloodBankDto userDto : users) {
			ResponseModel userModel = new ResponseModel();
			BeanUtils.copyProperties(userDto, userModel);
			returnValue.add(userModel);
		}
		
		logger.info("BloodBank List Provided!!");
		return returnValue;
	}
}

