package com.dxc.tks.api.service;

import com.dxc.tks.api.dao.UserRepository;
import com.dxc.tks.api.dto.UserDTO;
import com.dxc.tks.api.exception.UserNotFoundException;
import com.dxc.tks.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 
 * @author sindhu
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private UserRepository userRepository;

//	@Autowired
	//private PasswordEncoder bcryptPasswordEncoder;

	@Override
	public User saveUser(User user) throws NoSuchMessageException {
		Optional<User> userAlreadyExist = userRepository.findByEmailAddress(user.getEmailAddress());
		if (user != null && !userAlreadyExist.isEmpty()) {
			return userAlreadyExist.get();
		} else {
		//	user.setPassword(bcryptPasswordEncoder.encode(user.getPassword()));
			// user.setPassword(user.getPassword());
			return userRepository.save(user);
		}
	}

	@Override
	public UserDTO findByEmailAddress(String emailAddress) throws NoSuchMessageException, UserNotFoundException {
		Optional<User> user = userRepository.findByEmailAddress(emailAddress);
		if (user.isPresent())
			return new UserDTO(user.get());
		else {
			throw new UserNotFoundException(messageSource.getMessage("mts.user.not.found.message", null, null));
		}
	}

	@Override
	public User getUserById(long id) throws NoSuchMessageException, UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException(messageSource.getMessage("mts.user.not.found.message", null, null));
		}
		return user.get();
	}

	@Override
	public List<User> getUsers() {
		List<User> users = userRepository.findAll();
		return users;
	}

	@Override
	public User updateUser(User user) throws NoSuchMessageException, UserNotFoundException {
		User userFound = getUserById(user.getUserId());
		userFound.setEmailAddress(user.getEmailAddress());
		//userFound.setPassword(bcryptPasswordEncoder.encode(user.getPassword()));
		// userFound.setPassword(user.getPassword());
		userFound.setPhoneNumber(user.getPhoneNumber());

		return userRepository.save(userFound);
	}
}
