package com.sport.teams.services;

public interface IJwtService {
	public String getJwt();
	public String generateJwt(String username);
	public void checkJwt();
}
