package com.dxc.tks.api.exception;


@SuppressWarnings("serial")
public class IdNotFoundException extends RuntimeException{
	private long id;

    public IdNotFoundException(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
