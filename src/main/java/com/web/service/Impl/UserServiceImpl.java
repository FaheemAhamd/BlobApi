package com.web.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.el.stream.Stream;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.exception.ResourceNotFoundException;
import com.web.model.User;
import com.web.payload.UserDto;
import com.web.repository.UserRepo;
import com.web.service.UserService;

import net.bytebuddy.asm.Advice.This;
@Service
public class UserServiceImpl implements UserService {
@Autowired
	private UserRepo repo;

@Autowired 
private ModelMapper modelMapper;
	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
	User saveUser=	repo.save(user);
		return this.userToDto(saveUser);
	}

	@Override
	public UserDto updateuser(UserDto userDto, Integer userId) {
		User user=this.repo.getById(userId);
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updatedUser=this.repo.save(user);
		UserDto userDto2=this.userToDto(updatedUser);
		return userDto2;
	}

	@Override
	public UserDto getById(Integer id) {
		User user=this.repo.findById(id)
				.orElseThrow(
				()->new ResourceNotFoundException("Users", "id", id));
		// TODO Auto-generated method stub
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		
		
		List<User> users=this.repo.findAll();
		List<UserDto> userDtos2=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos2;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
	User user=	this.repo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "id", userId));
	this.repo.delete(user);
	}
	
	public User dtoToUser(UserDto userDto) {
User user=modelMapper.map(userDto, User.class);
		//		User user=new User();
//	user.setId(userDto.getId());
//	user.setName(userDto.getName());
//	user.setEmail(userDto.getEmail());
//	user.setPassword(userDto.getPassword());
//	user.setAbout(userDto.getAbout());
	return user;
	}
	
	public UserDto userToDto(User user) {
		UserDto userDto=modelMapper.map(user, UserDto.class);
//		UserDto userDto=new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//	userDto.setPassword(user.getPassword());
//	userDto.setAbout(user.getAbout());
	return userDto;
	
	}

}
