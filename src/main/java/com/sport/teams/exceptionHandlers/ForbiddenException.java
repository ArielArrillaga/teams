package com.sport.teams.exceptionHandlers;

import lombok.Getter;

@Getter
public class ForbiddenException extends RuntimeException {

	private static final long serialVersionUID = 1L;

    public ForbiddenException(String detail) {
        super(detail);
    }

}