package com.sport.teams.services;

import com.sport.teams.entitys.security.JwtResponse;
import com.sport.teams.entitys.security.LoginRequest;

public interface ISecurityService {
	public JwtResponse validateLogin (LoginRequest request);
}
