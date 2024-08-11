package com.sport.teams.entitys.security;

import lombok.Data;

@Data
public class LoginRequest {
	private String username;
	private String password;
	
	@Override
	public String toString() {
		return "LoginRequest [username=" + username + ", password= ignorada para log]";
	}
	
	
}
