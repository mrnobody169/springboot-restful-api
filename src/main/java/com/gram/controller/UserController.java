package com.gram.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gram.dto.CreateUserInDto;
import com.gram.dto.GetUserByEmailOutDto;
import com.gram.dto.GetUserByIdOutDto;
import com.gram.dto.GetUserByUserNameOrEmailOutDto;
import com.gram.dto.GetUserByUserNameOutDto;
import com.gram.dto.GetUserHasRoleOutDto;
import com.gram.dto.RetrieveUserOutDto;
import com.gram.dto.UpdateUserInDto;
import com.gram.dto.response.BaseResponse;
import com.gram.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Get User by ID", description = "Get User by ID")
    @RequestMapping(method = RequestMethod.GET, path = "/detail/{userId}")
    public ResponseEntity<BaseResponse<GetUserByIdOutDto>> getUserById(@PathVariable Integer userId) throws Exception {
    	GetUserByIdOutDto user =  userService.getUserById(userId);
        return new ResponseEntity<>(new BaseResponse<GetUserByIdOutDto>(HttpStatus.OK.value(), "success", user), HttpStatus.OK);
    }
    
    @Operation(summary = "Create User", description = "Create User")
    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public ResponseEntity<BaseResponse<Integer>> createUser(@RequestBody CreateUserInDto createUserInDto) throws Exception {
    	@SuppressWarnings("unused")
		int createdUser = userService.createUser(createUserInDto);
        return new ResponseEntity<>(new BaseResponse<Integer>(HttpStatus.CREATED.value(), "success", null), HttpStatus.CREATED);
    }
    
    @Operation(summary = "Retrieve User", description = "Retrieve User")
    @RequestMapping(method = RequestMethod.GET, path = "/retrieve")
    public ResponseEntity<BaseResponse<List<RetrieveUserOutDto>>> retrieveUser() throws Exception {
    	List<RetrieveUserOutDto> users =  userService.retrieveUser();
        return new ResponseEntity<>(new BaseResponse<List<RetrieveUserOutDto>>(HttpStatus.OK.value(), "success", users), HttpStatus.OK);
    }
    
    @Operation(summary = "Get User Has Role", description = "Get User Has Role")
    @RequestMapping(method = RequestMethod.GET, path = "/has-role")
    public ResponseEntity<BaseResponse<List<GetUserHasRoleOutDto>>> getUserHasRole() throws Exception {
    	List<GetUserHasRoleOutDto> users = userService.getUserHasRole();
        return new ResponseEntity<>(new BaseResponse<List<GetUserHasRoleOutDto>>(HttpStatus.OK.value(), "success", users), HttpStatus.OK);
    }
    
    @Operation(summary = "Update User", description = "Update User")
    @RequestMapping(method = RequestMethod.POST, path = "/update")
    public ResponseEntity<BaseResponse<Integer>> updateUser(@RequestBody UpdateUserInDto updateUserInDto) throws Exception {
        @SuppressWarnings("unused")
    	int user = userService.updateUser(updateUserInDto);
        return new ResponseEntity<>(new BaseResponse<Integer>(HttpStatus.OK.value(), "success", null), HttpStatus.OK);
    }
    
    @Operation(summary = "Delete User", description = "Delete User")
    @RequestMapping(method = RequestMethod.POST, path = "/delete/{id}")
    public ResponseEntity<BaseResponse<Integer>> deleteUser(@PathVariable Integer id) throws Exception {
        @SuppressWarnings("unused")
		int user = userService.deleteUser(id);
        return new ResponseEntity<>(new BaseResponse<Integer>(HttpStatus.NO_CONTENT.value(), "success", null), HttpStatus.NO_CONTENT);
        
    }
    
    @Operation(summary = "Retrieve User by Email", description = "Retrieve User by Email")
    @RequestMapping(method = RequestMethod.GET, path = "/email/{email}")
    public ResponseEntity<BaseResponse<GetUserByEmailOutDto>> getUserByEmail(@PathVariable String email) throws Exception {
    	GetUserByEmailOutDto user = userService.getUserByEmail(email);
        return new ResponseEntity<>(new BaseResponse<GetUserByEmailOutDto>(HttpStatus.OK.value(), "success", user), HttpStatus.OK);
    }
    
    @Operation(summary = "Retrieve User by Username", description = "Retrieve User by Username")
    @RequestMapping(method = RequestMethod.GET, path = "/username/{username}")
    public ResponseEntity<BaseResponse<GetUserByUserNameOutDto>> getUserByUserName(@PathVariable String username) throws Exception {
    	GetUserByUserNameOutDto user = userService.getUserByUserName(username);
        return new ResponseEntity<>(new BaseResponse<GetUserByUserNameOutDto>(HttpStatus.OK.value(), "success", user), HttpStatus.OK);
    }
    
    @Operation(summary = "Get User by Username or Email", description = "Get User by Username or Email")
    @RequestMapping(method = RequestMethod.GET, path = "/getuserbyusernameoremail")
    public ResponseEntity<BaseResponse<GetUserByUserNameOrEmailOutDto>> getUserByUserNameOrEmail(@RequestParam String param) throws Exception {
    	GetUserByUserNameOrEmailOutDto user = userService.getUserByUserNameOrEmail(param);
        return new ResponseEntity<>(new BaseResponse<GetUserByUserNameOrEmailOutDto>(HttpStatus.OK.value(), "success", user), HttpStatus.OK);
    }
}
