package com.sport.teams.controllers;


import org.springframework.http.ResponseEntity;

import com.sport.teams.entitys.security.JwtResponse;
import com.sport.teams.entitys.security.LoginRequest;

public interface ISecurityController {  
	public ResponseEntity<JwtResponse> login (LoginRequest request);
	public String prueba ();
}
