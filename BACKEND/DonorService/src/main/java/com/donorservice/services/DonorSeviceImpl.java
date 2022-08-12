package com.donorservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import com.donorservice.data.DonorEntity;
import com.donorservice.repository.DonorRepository;
import com.donorservice.shared.DonorDto;


@Service
public class DonorSeviceImpl implements DonorService {

	@Autowired
	private DonorRepository userRepository;

	Logger logger = LoggerFactory.getLogger(DonorSeviceImpl.class);
	@Override
	public DonorDto createUser(DonorDto userDetails) {
		// TODO Auto-generated method stub
		logger.debug("Setting a Random UserId");
		userDetails.setUserId(UUID.randomUUID().toString());
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		DonorEntity userEntity = modelMapper.map(userDetails, DonorEntity.class);

		this.userRepository.save(userEntity);
		logger.debug("Donor Entries Saved.");
		DonorDto userDto = modelMapper.map(userEntity, DonorDto.class);
		return userDto;
	}

	@Override
	public List<DonorDto> getUsers() {
		List<DonorDto> returnValue=new ArrayList<>();
		logger.debug("Calling the findAll() method from Donor Repository");
		Iterable<DonorEntity> users=this.userRepository.findAll();
		for (DonorEntity userEntity : users) {
			DonorDto userDto = new DonorDto();
			BeanUtils.copyProperties(userEntity, userDto);
			returnValue.add(userDto);
		}
		
		logger.debug("List of Donors Fetched");
		return returnValue;
	}

	@Override
	public ResponseEntity<HttpStatus> deleteDonor(String userId) {
		// try {
		logger.debug("calling inbuilt method findbyuserid() from donor repo");
		DonorEntity donorEntity = userRepository.findByUserId(userId);
		if (donorEntity == null) {
			// throw some exception
			logger.error("Donor Detail not found. check again!!");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		userRepository.delete(donorEntity);
		logger.debug("Donor deleted Successfully!!");
		return new ResponseEntity<>(HttpStatus.OK);

	}

}
