package com.dxc.tks.api.service;

import com.dxc.tks.api.dto.UserDTO;
import com.dxc.tks.api.exception.UserNotFoundException;
import com.dxc.tks.api.model.User;
import org.springframework.context.NoSuchMessageException;

import java.util.List;

/**
 * 
 * @author mkhan339
 *
 */
public interface UserService {

	public User saveUser(User user) throws NoSuchMessageException;

	public UserDTO findByEmailAddress(String emailAddress) throws NoSuchMessageException, UserNotFoundException;

	public User getUserById(long id) throws NoSuchMessageException, UserNotFoundException;

	public List<User> getUsers();

	public User updateUser(User user) throws NoSuchMessageException, UserNotFoundException;
}
