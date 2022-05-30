package com.web.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.sql.Template.NoOpColumnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.payload.ApiResponse;
import com.web.payload.UserDto;
import com.web.service.Impl.UserServiceImpl;
@RestController
@RequestMapping("/api/users")
public class UserController {
@Autowired
private	UserServiceImpl serviceImpl;
@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto createdUserDto=this.serviceImpl.createUser(userDto);
		return new ResponseEntity<>(createdUserDto,HttpStatus.CREATED);
	}

@PutMapping("/{userId}")
public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer userId){

UserDto userDto2=this.serviceImpl.updateuser(userDto, userId);
return  ResponseEntity.ok(userDto2);
}
@DeleteMapping("/{userId}")
public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId")Integer userId){
	this.serviceImpl.deleteUser(userId);
	return  new ResponseEntity<ApiResponse>(new ApiResponse("user deleted successfully",true),HttpStatus.OK);
}
@GetMapping("/")
public ResponseEntity<List<UserDto>> ListUsers(){
	
	return ResponseEntity.ok(this.serviceImpl.getAllUser());
}

@GetMapping("/{userId}")
public ResponseEntity<UserDto> findUserByid(@PathVariable("userId")Integer userId){
	return ResponseEntity.ok(this.serviceImpl.getById(userId));
}

}
