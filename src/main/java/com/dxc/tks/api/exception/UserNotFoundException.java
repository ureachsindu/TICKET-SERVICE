package com.dxc.tks.api.exception;

/**
 * 
 * @author mkhan339
 *
 */
public class UserNotFoundException extends Exception {
	private static final long serialVersionUID = 8140630514223432904L;

	private String errorMessage;

	public UserNotFoundException() {
		super();
	}

	public UserNotFoundException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}