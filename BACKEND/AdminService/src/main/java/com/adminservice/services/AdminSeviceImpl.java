package com.adminservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.adminservice.dto.AdminDto;
import com.adminservice.entity.AdminEntity;
import com.adminservice.repository.AdminRepository;



@Service
public class AdminSeviceImpl implements AdminService {

	@Autowired
	private AdminRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		AdminEntity userEntity = this.userRepository.findByEmail(username);
		if (userEntity == null) {
			throw new UsernameNotFoundException(username);
		}
		return new org.springframework.security.core.userdetails.User(userEntity.getEmail(),
				userEntity.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
	}

	@Override
	public AdminDto getUserDetailsByEmail(String userName) {
		AdminEntity userEntity = this.userRepository.findByEmail(userName);
		if (userEntity == null) {
			throw new UsernameNotFoundException(userName);
		}
		return new ModelMapper().map(userEntity, AdminDto.class);
	}
	
	@Override
	public AdminDto createUser(AdminDto userDetails) {
		// TODO Auto-generated method stub
		
		userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		AdminEntity userEntity = modelMapper.map(userDetails, AdminEntity.class);

		this.userRepository.save(userEntity);
		AdminDto userDto = modelMapper.map(userEntity, AdminDto.class);
		return userDto;
	}


}
