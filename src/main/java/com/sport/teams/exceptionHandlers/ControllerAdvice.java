package com.sport.teams.exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
	
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidJwtException.class)
    @ResponseBody
    public Response invalidJwt(InvalidJwtException ex) {

    	return new Response(ex.getMessage(), HttpStatus.UNAUTHORIZED.value());

    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            BadRequestException.class,
            org.springframework.web.bind.support.WebExchangeBindException.class,
            org.springframework.http.converter.HttpMessageNotReadableException.class,
            org.springframework.web.server.ServerWebInputException.class,
            org.springframework.web.HttpRequestMethodNotSupportedException.class,
            org.springframework.web.bind.MethodArgumentNotValidException.class,
            org.springframework.web.bind.MissingRequestHeaderException.class,
            org.springframework.web.bind.MissingServletRequestParameterException.class,
            java.util.InputMismatchException.class
    })
    @ResponseBody
    public Response badRequest(Exception ex) {
    	
    	return new Response(ex.getMessage(),  HttpStatus.BAD_REQUEST.value());

    }
    
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseBody
    public Response internalServerError(InternalServerErrorException ex) {

    	return new Response(ex.getMessage(),  HttpStatus.INTERNAL_SERVER_ERROR.value());

    }
    
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ForbiddenException.class)
    @ResponseBody
    public Response forbidden(ForbiddenException ex) {

    	return new Response(ex.getMessage(),  HttpStatus.FORBIDDEN.value());

    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(NoContent.class)
    @ResponseBody
    public void noContent(NoContent ex) {

    }
    
}
