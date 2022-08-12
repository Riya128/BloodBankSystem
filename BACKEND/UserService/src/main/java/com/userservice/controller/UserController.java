package com.userservice.controller;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.model.User;
import com.userservice.model.ResponseModel;
import com.userservice.services.UserService;
import com.userservice.shared.UserDto;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping("/{id}")
	public ResponseModel getUser(@PathVariable String id) {
		ResponseModel returnValue = new ResponseModel();
		logger.debug("calling Service class method getUserDetailsByUserId()");
		UserDto userDto = userService.getUserDetailsByUserId(id);
		BeanUtils.copyProperties(userDto, returnValue);
		//ModelMapper modelMapper = new ModelMapper();
		//returnValue = modelMapper.map(userDto, UserResponseModel.class);
		logger.info("Everything went well, Here are the details asked!");
		return returnValue;
	}


	@PutMapping(path = "/{id}")
	public ResponseModel updateUser(@PathVariable String id, @RequestBody ResponseModel responseModel) {
		ResponseModel returnValue = new ResponseModel();
		logger.debug("calling Service class method getUserDetailsByUserId()");
		UserDto userDto = userService.getUserDetailsByUserId(id);
		BeanUtils.copyProperties(responseModel, userDto);
		logger.debug("Service class method updateUser() called");
		UserDto updateUser = userService.updateUser(id, userDto);
		BeanUtils.copyProperties(updateUser, returnValue);
		logger.info("Details Updated successfully!!");
		return returnValue;
	}

	@PostMapping("/register")
	public ResponseEntity<ResponseModel> createUser(@RequestBody Object user) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto userDto = modelMapper.map(user, UserDto.class);
		logger.debug("Stored detials.\nCalling createUser() from service class");
		UserDto createdUser = userService.createUser(userDto);
		ResponseModel returnVal = modelMapper.map(createdUser, ResponseModel.class);
		logger.info("User Account Created Successfully!!");
		return ResponseEntity.status(HttpStatus.CREATED).body(returnVal);
	}

	@GetMapping("/allusers")
	public List<ResponseModel> getUsers() {
		List<ResponseModel> returnValue = new ArrayList<>();
		logger.debug("Service class method getUsers() called, retrieving User List");
		List<UserDto> users = userService.getUsers();

		for (UserDto userDto : users) {
			ResponseModel userModel = new ResponseModel();
			BeanUtils.copyProperties(userDto, userModel);
			returnValue.add(userModel);
		}

		logger.info("User List Provided!!");
		return returnValue;
	}
}
