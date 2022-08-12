package com.userservice.services;

import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.userservice.data.UserEntity;
import com.userservice.repository.UserRepository;
import com.userservice.shared.UserDto;

class UserServiceImplTest {

	String encryptedPassword = "poiusds";

	// this can't be mocked as we are testing it's methods only
	@InjectMocks
	UserSeviceImpl userService;

	@Mock // to create a fake repo
	UserRepository userRepository;

	@Mock
	BCryptPasswordEncoder bCryptPasswordEncoder;

	UserEntity userEntity;

	// runs before each test
	@BeforeEach
	void setUp() throws Exception {

		// to initiate mock
		MockitoAnnotations.initMocks(this);
		userEntity = new UserEntity();
		userEntity.setId(1L);
		userEntity.setEmail("demo@gmail.com");
		userEntity.setCity("city");
		userEntity.setAddress("address");
		userEntity.setEncryptedPassword(encryptedPassword);
		userEntity.setUserId("edsd3445tcd");
		userEntity.setPhoneNumber("1234567890");
		
	}

	@Test
	void testGetUserDetailsByEmail() {
		// when find by email is called on userRepo on any string it should return
		// entity
		// this is called mocking
		// mock obj is fake class where we can fake the output by giving some input
		when(userRepository.findByEmail(anyString())).thenReturn(userEntity);
		UserDto userDto = userService.getUserDetailsByEmail("test@gmail.com");
		assertNotNull(userDto);
		assertEquals("demo@gmail.com", userDto.getEmail());
	}

	// when exception is thrown on not finding a user
	@Test
	final void testGetUserDetailsByEmail_UsernameNotFoundException() {

		when(userRepository.findByEmail(anyString())).thenReturn(null);
		// userService.getUserDetailsByEmail("demo@gmail.com");
		assertThrows(UsernameNotFoundException.class, () -> {
			userService.getUserDetailsByEmail("demo@gmail.com");
		});
	}

	@Test
	final void testCreateUser() {

		when(bCryptPasswordEncoder.encode(anyString())).thenReturn(encryptedPassword);
		// when we save an entity class it should return a entity class
		when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

		UserDto userDto = new UserDto();
		UserDto storedDetails = userService.createUser(userDto);

		assertNotNull(storedDetails);
		// checks if the value of name that i have inserted is same as that being stored
		assertEquals(userEntity.getName(), storedDetails.getName());

	}

	@Test
	final void testGetUserDetailsByUserId() {
		when(userRepository.findByUserId(anyString())).thenReturn(userEntity);
		UserDto userDto = userService.getUserDetailsByUserId("edsd3445tcd");
		assertNotNull(userDto);
		assertEquals("edsd3445tcd", userDto.getUserId());
	}
	
	@Test
	final void testUpdateUser() {
		when(userRepository.findByUserId("edsd3445tcd")).thenReturn(userEntity);
		when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
		
		UserDto userDto = new UserDto();
		
		userDto.setAddress("address");
		userDto.setCity("city");
		userDto.setEmail("test@gmail.com");
		
		UserDto storedDetails = userService.updateUser("edsd3445tcd", userDto);

		assertNotNull(storedDetails);
		assertEquals("city", userDto.getCity());
	}
	
//    @Test
//    final void testGetUsers() {​​​​​​
//        when(userRepository.findAll()).thenReturn(Stream.of(userEntity).collect(Collectors.toList()));       
//        assertEquals(1, userService.getUsers().size());
//    }​​​​​​
   

	@Test
	final void testLoadUserByName() {
		when(userRepository.findByEmail(anyString())).thenReturn(userEntity);
		UserDetails userDetails=userService.loadUserByUsername("test@gmail.com");
		assertNotNull(userDetails);
		
	}
}

