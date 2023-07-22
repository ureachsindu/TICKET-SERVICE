package com.dxc.tks.api.resource;

import com.dxc.tks.api.dto.BaseResponse;
import com.dxc.tks.api.dto.UserDTO;
import com.dxc.tks.api.enums.SecurityError;
import com.dxc.tks.api.exception.ApplicationCustomException;
import com.dxc.tks.api.exception.UserNotFoundException;
import com.dxc.tks.api.model.User;
import com.dxc.tks.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private MessageSource source;

	@Autowired
	private UserService userService;

	/**
	 * This method is created to add user in the database.
	 * 
	 * @param user holds the user information
	 * @return response entity object
	 * @throws NoSuchMessageException     when no key is present
	 * @throws ApplicationCustomException application specific exception
	 */
	@PostMapping("/add")
	@Operation(summary = "Api to add new user")
	public ResponseEntity<?> saveUser(@RequestBody User user)
			throws NoSuchMessageException, ApplicationCustomException {
		final User userResponse = userService.saveUser(user);
		if (userResponse != null) {
			return new ResponseEntity<Object>(new BaseResponse(HttpStatus.CREATED.value(),
					source.getMessage("tks.success.message", null, null), userResponse), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Object>(new BaseResponse(HttpStatus.BAD_REQUEST.value(),
					SecurityError.OPERATION_FAILED.getDescription(), null), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * This method is to get the user information based on the user id
	 * 
	 * @param id user id of the user
	 * @return response entity object
	 * @throws NoSuchMessageException     when no key is present
	 * @throws ApplicationCustomException application specific exception
	 */
	@GetMapping("/{id}")
	@Operation(summary = "Api to the user information based on the user id")
	public ResponseEntity<?> getUser(@PathVariable long id) throws NoSuchMessageException, ApplicationCustomException {
		User userResponse;
		try {
			userResponse = userService.getUserById(id);
			if (userResponse != null) {
				return new ResponseEntity<Object>(new BaseResponse(HttpStatus.OK.value(),
						source.getMessage("tks.success.message", null, null), userResponse), HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>(new BaseResponse(HttpStatus.NOT_FOUND.value(),
						SecurityError.OPERATION_FAILED.getDescription(), null), HttpStatus.NOT_FOUND);
			}
		} catch (UserNotFoundException e) {
			return new ResponseEntity<Object>(
					new BaseResponse(HttpStatus.NOT_FOUND.value(), SecurityError.OPERATION_FAILED.getDescription(),
							source.getMessage("tks.user.not.found.message", null, null)),
					HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * This method is created to get list of users
	 * 
	 * @return response entity object
	 */
	@GetMapping
	@Operation(summary = "Api to get list of users")
	public ResponseEntity<?> getUsers() {
		final List<User> usersResponse = userService.getUsers();
		if (usersResponse != null) {
			return new ResponseEntity<Object>(new BaseResponse(HttpStatus.OK.value(),
					source.getMessage("tks.success.message", null, null), usersResponse), HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(new BaseResponse(HttpStatus.NOT_FOUND.value(),
					SecurityError.OPERATION_FAILED.getDescription(), null), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * This method is created to update the user information
	 * 
	 * @param user holds the information of the user
	 * @return updated user
	 * @throws NoSuchMessageException     when no key is present
	 * @throws ApplicationCustomException application specific exception
	 */
	@PutMapping("/update")
	@Operation(summary = "Api to update user details")
	public ResponseEntity<?> updateUser(@RequestBody User user)
			throws NoSuchMessageException, ApplicationCustomException {
		User userResponse;
		try {
			userResponse = userService.updateUser(user);
			if (userResponse != null) {
				return new ResponseEntity<Object>(new BaseResponse(HttpStatus.OK.value(),
						source.getMessage("tks.update.message", null, null), userResponse), HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>(new BaseResponse(HttpStatus.BAD_REQUEST.value(),
						SecurityError.OPERATION_FAILED.getDescription(), null), HttpStatus.BAD_REQUEST);
			}
		} catch (UserNotFoundException e) {
			return new ResponseEntity<Object>(
					new BaseResponse(HttpStatus.NOT_FOUND.value(), SecurityError.OPERATION_FAILED.getDescription(),
							source.getMessage("tks.user.not.found.message", null, null)),
					HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/user")
	@Operation(summary = "Api to get user details based on email address")
	public ResponseEntity<?> getUserByEmail(@RequestParam(name = "email") String emailAddress)
			throws NoSuchMessageException, ApplicationCustomException {
		UserDTO userDTOResponse;
		try {
			userDTOResponse = userService.findByEmailAddress(emailAddress);
			if (userDTOResponse != null) {
				return new ResponseEntity<Object>(new BaseResponse(HttpStatus.OK.value(),
						source.getMessage("tks.success.message", null, null), userDTOResponse), HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>(new BaseResponse(HttpStatus.BAD_REQUEST.value(),
						SecurityError.OPERATION_FAILED.getDescription(), null), HttpStatus.BAD_REQUEST);
			}
		} catch (UserNotFoundException e) {
			return new ResponseEntity<Object>(
					new BaseResponse(HttpStatus.NOT_FOUND.value(), SecurityError.OPERATION_FAILED.getDescription(),
							source.getMessage("tks.user.not.found.message", null, null)),
					HttpStatus.NOT_FOUND);
		}
	}
}
