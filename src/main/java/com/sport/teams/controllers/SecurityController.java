package com.sport.teams.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sport.teams.exceptionHandlers.InternalServerErrorException;
import com.sport.teams.exceptionHandlers.InvalidJwtException;
import com.sport.teams.exceptionHandlers.NoContent;
import com.sport.teams.exceptionHandlers.BadRequestException;

@RestController
@RequestMapping("/")
public class SecurityController {
	 
	 @GetMapping("/prueba/{ex}")
	 public ResponseEntity<String> prueba(@PathVariable("ex") String ex){
		 switch (ex) {
		case "una": {
			
			throw new InvalidJwtException();
		}
	case "bad": {
			
			throw new BadRequestException("Datos incorrectos.");
		}
	case "int": {
		
		throw new InternalServerErrorException();
	}
	case "noc": {
		
		throw new NoContent();
	}

		default:
			
			return null;
		}
	 }
}
