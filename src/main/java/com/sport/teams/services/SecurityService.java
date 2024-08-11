package com.sport.teams.services;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.security.Key;
import io.jsonwebtoken.security.Keys;

import com.sport.teams.entitys.security.JwtResponse;
import com.sport.teams.entitys.security.LoginRequest;
import com.sport.teams.entitys.security.UserApp;
import com.sport.teams.exceptionHandlers.InternalServerErrorException;
import com.sport.teams.exceptionHandlers.UnauthorizedException;
import com.sport.teams.repositories.UsersRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class SecurityService implements ISecurityService {
	
	//secret key para firmar el jwt
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    
	private static final Logger log = LoggerFactory.getLogger(SecurityService.class);

    @Autowired
    private UsersRepository usersRepository;
    
	@Override
	public JwtResponse validateLogin(LoginRequest request) {

		//Normalizo el username y obtengo la contraseña guardada
		String username = request.getUsername().toLowerCase();
        String dbPassword = this.getUserPassword(username);
        
        //Comparo la contraseña de la db con la ingresada
        if (!new BCryptPasswordEncoder().matches(request.getPassword(), dbPassword)) {
        	log.info("SecurityService: validateLogin: La contraseña no coincide.");
        	throw new UnauthorizedException();
        }

        String jwt = this.generateJwt(username);
		
		return new JwtResponse(jwt);
	}
	
	private String generateJwt(String username) {
        try {
            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);
            Date expirationDate = new Date(nowMillis + 600000); // 10 minutos de expiración

            return Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(now) // Fecha de creación
                    .setExpiration(expirationDate) // Fecha de vencimiento (10 minutos después)
                    .signWith(key) // Firma con la clave secreta
                    .compact();
        } catch (Exception e) {
        	log.error("SecurityService: genereteJwt: Error al generar jwt, motivo: " + e);
            throw new InternalServerErrorException("Error al crear JWT en login.");
        }
    }

	private String getUserPassword (String username) {
        UserApp usuario = usersRepository.findByUsername(username);

        if (usuario == null) {
        	log.error("SecurityService: getUserPassword: Error el usuario "+username+" no existe.");
        	throw new UnauthorizedException();
        }
        
        return usuario.getPassword();
	}

}
