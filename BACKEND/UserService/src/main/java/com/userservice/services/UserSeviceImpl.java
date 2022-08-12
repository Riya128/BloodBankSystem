package com.userservice.services;

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

import com.userservice.data.UserEntity;
import com.userservice.repository.UserRepository;
import com.userservice.shared.UserDto;

@Service
public class UserSeviceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	Logger logger = LoggerFactory.getLogger(UserSeviceImpl.class);
	
	@Override
	public UserDto createUser(UserDto userDetails) {
		// TODO Auto-generated method stub
		logger.debug("Setting a Random UserId");
		userDetails.setUserId(UUID.randomUUID().toString());
		logger.debug("Setting encrypted password");
		userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);

		this.userRepository.save(userEntity);
		logger.debug("User Entries Saved.");
		UserDto userDto = modelMapper.map(userEntity, UserDto.class);
		return userDto;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.debug("Calling inbuilt function findByEmail()");
		UserEntity userEntity = this.userRepository.findByEmail(username);
		if (userEntity == null) {
			logger.error("User Not Found!!");
			throw new UsernameNotFoundException(username);
		}
		logger.debug("returning entry from service class");
		return new org.springframework.security.core.userdetails.User(userEntity.getEmail(),
				userEntity.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
	}

	@Override
	public UserDto getUserDetailsByEmail(String email) {
		logger.debug("calling inbuilt methoda findByEmail()");
		UserEntity userEntity = this.userRepository.findByEmail(email);
		if (userEntity == null) {
			logger.error("User Not Found!!");
			throw new UsernameNotFoundException(email);
		}
		logger.debug("returning entry from service class");
		return new ModelMapper().map(userEntity, UserDto.class);
	}

	@Override
	public UserDto getUserDetailsByUserId(String userId) {
		logger.debug("calling inbuilt methoda findByUserId()");
//		UserDto returnValue = new UserDto();
		UserEntity userEntity = this.userRepository.findByUserId(userId);

		if (userEntity == null) {
			logger.error("User Not Found!!");
			throw new UsernameNotFoundException("User with ID: " + userId + " not found");
		}
		logger.debug("returning entry from service class");
		return new ModelMapper().map(userEntity, UserDto.class);
		// BeanUtils.copyProperties(userEntity, returnValue);

		// return returnValue;
	}

	@Override
	public UserDto updateUser(String userId, UserDto user) {
		logger.debug("Calling inbuilt function findByUserId()");
		UserEntity userEntity = this.userRepository.findByUserId(userId);

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
		UserEntity updatedUserDetails = userRepository.save(userEntity);
		return new ModelMapper().map(updatedUserDetails, UserDto.class);

	}

	@Override
	public List<UserDto> getUsers() {
		
		List<UserDto> returnValue=new ArrayList<>();
		logger.debug("Calling the findAll() method from user Repository");
		Iterable<UserEntity> users=this.userRepository.findAll();
		for (UserEntity userEntity : users) {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(userEntity, userDto);
			returnValue.add(userDto);
		}
		logger.debug("List of Users Fetched");
		return returnValue;
	}



}
