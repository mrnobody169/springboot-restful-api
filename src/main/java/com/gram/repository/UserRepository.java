package com.gram.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gram.dto.CreateUserInDto;
import com.gram.dto.GetUserByEmailOutDto;
import com.gram.dto.GetUserByIdOutDto;
import com.gram.dto.GetUserByUserNameOrEmailOutDto;
import com.gram.dto.GetUserByUserNameOutDto;
import com.gram.dto.GetUserHasRoleOutDto;
import com.gram.dto.RetrieveUserOutDto;
import com.gram.dto.UpdateUserInDto;

@Mapper
public interface UserRepository {
	public GetUserByIdOutDto getUserById(Integer userId) throws Exception;
	public int createUser(CreateUserInDto createUserInDto) throws Exception;
	public List<RetrieveUserOutDto> retrieveUser() throws Exception;
	public List<GetUserHasRoleOutDto> getUserHasRole() throws Exception;
	public int updateUser(UpdateUserInDto updateUserInDto) throws Exception;
	public int deleteUser(Integer id) throws Exception;
	public GetUserByEmailOutDto getUserByEmail(String email) throws Exception;
	public GetUserByUserNameOutDto getUserByUserName(String username) throws Exception;
	public GetUserByUserNameOrEmailOutDto getUserByUserNameOrEmail(String param) throws Exception;
}
