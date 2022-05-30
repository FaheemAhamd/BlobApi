package com.web.service;

import java.util.List;

import com.web.payload.UserDto;

public interface UserService {
	UserDto createUser(UserDto userDto);
	UserDto updateuser(UserDto userDto,Integer userId);
	UserDto getById(Integer id);
	List<UserDto> getAllUser();
	void deleteUser(Integer userId);

}
