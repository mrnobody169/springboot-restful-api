package com.gram.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gram.dto.CreateUserInDto;
import com.gram.dto.GetUserByIdOutDto;
import com.gram.dto.GetUserHasRoleOutDto;
import com.gram.dto.RetrieveUserOutDto;
import com.gram.dto.UpdateUserInDto;
import com.gram.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Get User by ID", description = "Get User by ID")
    @RequestMapping(method = RequestMethod.GET, path = "/detail/{userId}")
    public GetUserByIdOutDto getUserById(@PathVariable Integer userId) throws Exception {
        return userService.getUserById(userId);
    }
    
    @Operation(summary = "Create User", description = "Create User")
    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public int createUser(@RequestBody CreateUserInDto createUserInDto) throws Exception {
        return userService.createUser(createUserInDto);
    }
    
    @Operation(summary = "Retrieve User", description = "Retrieve User")
    @RequestMapping(method = RequestMethod.GET, path = "/retrieve")
    public List<RetrieveUserOutDto> retrieveUser() throws Exception {
        return userService.retrieveUser();
    }
    
    @Operation(summary = "Get User Has Role", description = "Get User Has Role")
    @RequestMapping(method = RequestMethod.GET, path = "/has-role")
    public List<GetUserHasRoleOutDto> getUserHasRole() throws Exception {
        return userService.getUserHasRole();
    }
    
    @Operation(summary = "Update User", description = "Update User")
    @RequestMapping(method = RequestMethod.POST, path = "/update")
    public int updateUser(@RequestBody UpdateUserInDto updateUserInDto) throws Exception {
        return userService.updateUser(updateUserInDto);
    }
    
    @Operation(summary = "Delete User", description = "Delete User")
    @RequestMapping(method = RequestMethod.POST, path = "/delete/{id}")
    public int deleteUser(@PathVariable Integer id) throws Exception {
        return userService.deleteUser(id);
    }
}
