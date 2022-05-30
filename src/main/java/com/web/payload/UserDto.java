package com.web.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;
	@NotEmpty
	@Size(min = 4,message = "Name should be min 4 chars")
	private String name;
	@Email(message = "email address is not valid !!")
	private String email;
	@NotEmpty
	@Size(min = 4,max = 10,message = "password should be min 4 char and max 10")
	private String password;
	@NotEmpty
	private String about;
}
