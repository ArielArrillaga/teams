package com.sport.teams.exceptionHandlers;

public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnauthorizedException() {
		super("Error, verifique los datos y vuelva a intentarlo.");
	}

    public UnauthorizedException(String detail) {
        super(detail);
    }
}
