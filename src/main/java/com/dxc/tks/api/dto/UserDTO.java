package com.dxc.tks.api.dto;

import com.dxc.tks.api.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserDTO implements Serializable {

	private static final long serialVersionUID = -3073513901247704793L;

	private Long userId;
	private String username;
	private String password;
	private String emailAddress;
	private String phoneNumber;

	public UserDTO(User user) {
		this.userId = user.getUserId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.emailAddress = user.getEmailAddress();
		this.phoneNumber = user.getPhoneNumber();
	}

}
