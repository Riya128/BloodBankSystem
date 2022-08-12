package com.hospitalservice.services;

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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hospitalservice.data.HospitalEntity;
import com.hospitalservice.repository.HospitalRepository;
import com.hospitalservice.shared.HospitalDto;


@Service
public class HospitalSeviceImpl implements HospitalService {

	@Autowired
	private HospitalRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	Logger logger = LoggerFactory.getLogger(HospitalSeviceImpl.class);
	
	@Override
	public HospitalDto createUser(HospitalDto userDetails) {
		// TODO Auto-generated method stub
		logger.debug("Setting a Random UserId");
		userDetails.setUserId(UUID.randomUUID().toString());
		logger.debug("Setting encrypted password");
		userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		HospitalEntity userEntity = modelMapper.map(userDetails, HospitalEntity.class);

		this.userRepository.save(userEntity);
		logger.debug("User Entries Saved.");
		HospitalDto userDto = modelMapper.map(userEntity, HospitalDto.class);
		return userDto;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		HospitalEntity userEntity = this.userRepository.findByEmail(username);
		if (userEntity == null) {
			logger.error("User Not Found!!");
			throw new UsernameNotFoundException(username);
		}
		logger.debug("returning entry from service class");
		return new org.springframework.security.core.userdetails.User(userEntity.getEmail(),
				userEntity.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
	}

	@Override
	public HospitalDto getUserDetailsByEmail(String email) {
		logger.debug("calling inbuilt methoda findByEmail()");
		HospitalEntity userEntity = this.userRepository.findByEmail(email);
		if (userEntity == null) {
			logger.error("User Not Found!!");
			throw new UsernameNotFoundException(email);
		}
		logger.debug("Returning entry from service class");
		return new ModelMapper().map(userEntity, HospitalDto.class);
	}

	@Override
	public HospitalDto getUserDetailsByUserId(String userId) {

//		UserDto returnValue = new UserDto();
		logger.debug("calling inbuilt methoda findByUserId()");
		HospitalEntity userEntity = this.userRepository.findByUserId(userId);

		if (userEntity == null) {
			logger.error("User Not Found!!");
			throw new UsernameNotFoundException("User with ID: " + userId + " not found");
		}
		logger.debug("Returning Entry from service class");
		return new ModelMapper().map(userEntity, HospitalDto.class);
		// BeanUtils.copyProperties(userEntity, returnValue);

		// return returnValue;
	}

	@Override
	public HospitalDto updateUser(String userId, HospitalDto user) {
		HospitalEntity userEntity = this.userRepository.findByUserId(userId);

		if (userEntity == null) {
			logger.error("User Not Found!!");
			throw new UsernameNotFoundException("User with ID: " + userId + " not found");
		}
		
		logger.debug("Setting new values");
		userEntity.setName(user.getName());
		userEntity.setCity(user.getCity());
		userEntity.setAddress(user.getAddress());
		userEntity.setPhoneNumber(user.getPhoneNumber());

		logger.info("new details set successfully");
		HospitalEntity updatedUserDetails = userRepository.save(userEntity);
		return new ModelMapper().map(updatedUserDetails, HospitalDto.class);

	}

	@Override
	public List<HospitalDto> getUsers() {
		List<HospitalDto> returnValue=new ArrayList<>();
		logger.debug("Calling the finfAll() method from user Repository");
		Iterable<HospitalEntity> users=this.userRepository.findAll();
		for (HospitalEntity userEntity : users) {
			HospitalDto userDto = new HospitalDto();
			BeanUtils.copyProperties(userEntity, userDto);
			returnValue.add(userDto);
		}
		logger.debug("List of Hospitals Fetched");
		return returnValue;
	}

}
