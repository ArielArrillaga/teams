package com.sport.teams.exceptionHandlers;

public class InvalidJwtException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
    public InvalidJwtException() {
        super("Ocurrió un error, por favor vuelva a iniciar sesión");
    }
}
