package com.bloodbankservice.services;

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

import com.bloodbankservice.data.BloodBankEntity;
import com.bloodbankservice.repository.BloodBankRepository;
import com.bloodbankservice.shared.BloodBankDto;


@Service
public class BloodBankSeviceImpl implements BloodBankService {

	@Autowired
	private BloodBankRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	Logger logger = LoggerFactory.getLogger(BloodBankSeviceImpl.class);

	@Override
	public BloodBankDto createUser(BloodBankDto userDetails) {
		// TODO Auto-generated method stub
		logger.debug("Setting a Random UserId");
		userDetails.setUserId(UUID.randomUUID().toString());
		logger.debug("Setting encrypted password");
		userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		BloodBankEntity userEntity = modelMapper.map(userDetails, BloodBankEntity.class);

		this.userRepository.save(userEntity);
		logger.debug("User Entries Saved.");
		BloodBankDto userDto = modelMapper.map(userEntity, BloodBankDto.class);
		return userDto;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.debug("Calling inbuilt function findByEmail()");
		BloodBankEntity userEntity = this.userRepository.findByEmail(username);
		if (userEntity == null) {
			logger.error("User Not Found!!");
			throw new UsernameNotFoundException(username);
		}
		logger.debug("returning entry from service class");
		return new org.springframework.security.core.userdetails.User(userEntity.getEmail(),
				userEntity.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
	}

	@Override
	public BloodBankDto getUserDetailsByEmail(String email) {
		logger.debug("calling inbuilt method findByEmail()");
		BloodBankEntity userEntity = this.userRepository.findByEmail(email);
		if (userEntity == null) {
			logger.error("User Not Found!!");
			throw new UsernameNotFoundException(email);
		}
		logger.debug("returning entry from service class");
		return new ModelMapper().map(userEntity, BloodBankDto.class);
	}

	@Override
	public BloodBankDto getUserDetailsByUserId(String userId) {

//		UserDto returnValue = new UserDto();
		logger.debug("calling inbuilt methods findByUserId()");
		BloodBankEntity userEntity = this.userRepository.findByUserId(userId);

		if (userEntity == null) {
			logger.error("User Not Found!!");
			throw new UsernameNotFoundException("User with ID: " + userId + " not found");
		}
		logger.debug("returning entry from service class");
		return new ModelMapper().map(userEntity, BloodBankDto.class);
		// BeanUtils.copyProperties(userEntity, returnValue);

		// return returnValue;
	}

	@Override
	public BloodBankDto updateUser(String userId, BloodBankDto user) {
		logger.debug("Calling inbuilt function findByUserId()");
		BloodBankEntity userEntity = this.userRepository.findByUserId(userId);

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
		BloodBankEntity updatedUserDetails = userRepository.save(userEntity);
		return new ModelMapper().map(updatedUserDetails, BloodBankDto.class);

	}

	@Override
	public List<BloodBankDto> getUsers() {
		List<BloodBankDto> returnValue=new ArrayList<>();
		logger.debug("Calling the findAll() method from user Repository");
		Iterable<BloodBankEntity> users=this.userRepository.findAll();
		for (BloodBankEntity userEntity : users) {
			BloodBankDto userDto = new BloodBankDto();
			BeanUtils.copyProperties(userEntity, userDto);
			returnValue.add(userDto);
		}
		logger.debug("List of Bloodbanks Fetched");
		return returnValue;
	}

}
