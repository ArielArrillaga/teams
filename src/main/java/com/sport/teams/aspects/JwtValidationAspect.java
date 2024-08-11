package com.sport.teams.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sport.teams.services.IJwtService;

@Aspect
@Component
public class JwtValidationAspect {
	
	@Autowired
	IJwtService jwtService;
	
	@Before("@annotation(com.sport.teams.anotations.ValidateJwt)")
    public void validateJwt() throws Throwable {
        jwtService.checkJwt();
	}
}
	

	 
