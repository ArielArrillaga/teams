package com.sport.teams.services;

import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sport.teams.exceptionHandlers.InternalServerErrorException;
import com.sport.teams.exceptionHandlers.InvalidJwtException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtService implements IJwtService {
	
	//secret key para firmar el jwt
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    
	private static final Logger log = LoggerFactory.getLogger(JwtService.class);

	@Autowired
	HttpServletRequest request;
	
	/**
	 * Obtiene el jwt desde el header
	 */
	@Override
	public String getJwt () {
		try {
			
			String jwt = request.getHeader("Authorization");
			
			if (jwt.startsWith("Bearer ")) {
	    		jwt = jwt.substring(7); // Quítale el prefijo "Bearer "
	    	}
			return jwt;
			
		}catch (Exception e) {
			log.error("JwtService: getJwt: Error: No fue posible recuperar el token del header: "+e);
			throw new InvalidJwtException();
		}
	}

	/**
	 * Genera el jwt con el username, fecha de creacion y fecha de expiracion.
	 */
	@Override
	public String generateJwt(String username) {
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

	/**
	 * Verifica que el jwt se encuentre en un estado valido y que no este vencido
	 */
	@Override
	public void checkJwt() {
		String token = this.getJwt();
		Claims claims = null;
		 try {
	            // Parsear y validar el JWT
	            claims = Jwts.parserBuilder()
	                    .setSigningKey(key)
	                    .build()
	                    .parseClaimsJws(token)
	                    .getBody();

		 }
		 catch (Exception e) {
             log.error("JwtService: checkJwt: Error al validar jwt");
             throw new InvalidJwtException("Jwt invalido, vuelva a iniciar sesión.");
		 } 

         // Verificar la expiración del token
         if (claims.getExpiration().before(new Date())) {
             log.info("JwtService: checkJwt: El jwt esta vencido.");
             throw new InvalidJwtException("Jwt vencido, vuelva a iniciar sesión.");
         }
	}

}
