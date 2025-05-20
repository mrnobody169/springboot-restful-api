package com.gram.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gram.dto.CreateUserInDto;
import com.gram.dto.GetUserByIdOutDto;
import com.gram.dto.GetUserHasRoleOutDto;
import com.gram.dto.RetrieveUserOutDto;
import com.gram.dto.UpdateUserInDto;
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
        return userRepository.getUserById(userId);
    }
    
    // Create User
    @Transactional(rollbackFor = Exception.class)
    public int createUser(CreateUserInDto createUserInDto) throws Exception {
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
        return userRepository.updateUser(updateUserInDto);
    }
    
    // Delete User
    @Transactional(rollbackFor = Exception.class)
    public int deleteUser(Integer id) throws Exception {
        return userRepository.deleteUser(id);
    }
}
