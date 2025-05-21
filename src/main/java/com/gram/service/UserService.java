package com.gram.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gram.dto.CreateUserInDto;
import com.gram.dto.GetUserByEmailOutDto;
import com.gram.dto.GetUserByIdOutDto;
import com.gram.dto.GetUserByUserNameOrEmailOutDto;
import com.gram.dto.GetUserByUserNameOutDto;
import com.gram.dto.GetUserHasRoleOutDto;
import com.gram.dto.RetrieveUserOutDto;
import com.gram.dto.UpdateUserInDto;
import com.gram.exception.BadRequestException;
import com.gram.exception.NotFoundException;
import com.gram.repository.UserRepository;

@Service("userService")
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    // Retrieve User by ID
    @Transactional(readOnly = true)
    public GetUserByIdOutDto getUserById(Integer userId) throws Exception {
    	logger.atInfo();
    	GetUserByIdOutDto user = userRepository.getUserById(userId);
    	if (user != null)
    	{
    		return user;    		
    	}
    	throw new NotFoundException("Not Found User");	
    }
    
    // Create User
    @Transactional(rollbackFor = Exception.class)
    public int createUser(CreateUserInDto createUserInDto) throws Exception {
    	this.isExisted(createUserInDto.getUsername(),createUserInDto.getEmail());
        return userRepository.createUser(createUserInDto);
    }
    
    // Retrieve User
    @Transactional(readOnly = true)
    public List<RetrieveUserOutDto> retrieveUser() throws Exception {
        return userRepository.retrieveUser();
    }
    
    // Get User Has Role
    @Transactional(readOnly = true)
    public List<GetUserHasRoleOutDto> getUserHasRole() throws Exception {
        return userRepository.getUserHasRole();
    }
    
    // Update User
    @Transactional(rollbackFor = Exception.class)
    public int updateUser(UpdateUserInDto updateUserInDto) throws Exception {
    	@SuppressWarnings("unused")
		GetUserByIdOutDto user = this.getUserById(updateUserInDto.getId());
    	
    	this.isExisted(updateUserInDto.getUsername(),updateUserInDto.getEmail());
    	// if (userExisted)
    	// {
    	// 	throw new BadRequestException("UserName/Email already exists.");
    	// }
        return userRepository.updateUser(updateUserInDto);
    }
    
    // Delete User
    @Transactional(rollbackFor = Exception.class)
    public int deleteUser(Integer id) throws Exception {
        return userRepository.deleteUser(id);
    }
    
    // Retrieve User by Email
    @Transactional(readOnly = true)
    public GetUserByEmailOutDto getUserByEmail(String email) throws Exception {
    	GetUserByEmailOutDto user = userRepository.getUserByEmail(email);
        if (user != null)
    	{
    		return user;
    	}
    	throw new NotFoundException("Not Found User");
    }
    
    // Retrieve User by UserName
    @Transactional(readOnly = true)
    public GetUserByUserNameOutDto getUserByUserName(String username) throws Exception {
    	GetUserByUserNameOutDto user = userRepository.getUserByUserName(username);
    	if (user != null)
    	{
    		return user;
    	}
    	throw new NotFoundException("Not Found User");
    }
    
	// Retrieve User by UserName or Email
    @Transactional(readOnly = true)
    public GetUserByUserNameOrEmailOutDto getUserByUserNameOrEmail(String param) throws Exception {
    	return userRepository.getUserByUserNameOrEmail(param);	
    }
    
    private boolean isExisted(String userName, String email) throws Exception {
    	GetUserByUserNameOutDto userByUserName = this.getUserByUserName(userName);
        GetUserByEmailOutDto userByEmail = this.getUserByEmail(email);
    	if (userByUserName != null )
    	{
    		throw new BadRequestException("UserName already exists.");
    	}
    	if (userByEmail != null)
    	{
    		throw new BadRequestException("Email already exists.");
    	}
    	return false;
    }
}
