package com.sport.teams.exceptionHandlers;

public class InternalServerErrorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	 public InternalServerErrorException(String detail) {
	        super(detail);
	 } 
	 public InternalServerErrorException() {
	        super("Lo sentimos, ocurrió un error, vuelva a intentarlo en un instante.");
	 }
    

}