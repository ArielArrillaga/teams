package com.sport.teams.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sport.teams.entitys.security.JwtResponse;
import com.sport.teams.entitys.security.LoginRequest;
import com.sport.teams.services.ISecurityService;

@RestController
@RequestMapping("/auth")
public class SecurityController implements ISecurityController{
	private static final Logger log = LoggerFactory.getLogger(SecurityController.class);
	
	@Autowired
	ISecurityService securityService;
	
	@Override
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request) {
		log.info("SecurityController: login: iniciado para user: "+request.getUsername());
		return ResponseEntity.ok(securityService.validateLogin(request));
	}
}
