package com.sport.teams.exceptionHandlers;

public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

    public BadRequestException(String detail) {
        super(detail);
    }

}