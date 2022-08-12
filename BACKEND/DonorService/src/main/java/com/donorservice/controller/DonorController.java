package com.donorservice.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.donorservice.model.Donor;
import com.donorservice.model.ResponseModel;
import com.donorservice.services.DonorService;
import com.donorservice.shared.DonorDto;


@RestController
@RequestMapping("/donor")
@CrossOrigin
public class DonorController {

	@Autowired
	private DonorService userService;
	
	Logger logger = LoggerFactory.getLogger(DonorController.class);

	@PostMapping
	public ResponseEntity<ResponseModel> createUser(@RequestBody Donor user) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		DonorDto userDto = modelMapper.map(user, DonorDto.class);
		logger.debug("Stored detials.\nCalling createUser() from service class");
		DonorDto createdUser = userService.createUser(userDto);
		ResponseModel returnVal = modelMapper.map(createdUser, ResponseModel.class);
		logger.info("Donor Registration Successfully!!");
		return ResponseEntity.status(HttpStatus.CREATED).body(returnVal);
	}

	@GetMapping("/alldonors")
	public List<ResponseModel> getUsers() {
		List<ResponseModel> returnValue = new ArrayList<>();
		logger.debug("Service class method getUsers() called, retrieving Donor List");
		List<DonorDto> users = userService.getUsers();

		for (DonorDto userDto : users) {
			ResponseModel userModel = new ResponseModel();
			BeanUtils.copyProperties(userDto, userModel);
			returnValue.add(userModel);
		}

		logger.info("Donor List Provided!!");
		return returnValue;
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<HttpStatus> deleteDonor(@PathVariable String id) {
		System.out.println(id);
		logger.info("Donor Removed Successfully!!");
		return this.userService.deleteDonor(id);

	}

}
