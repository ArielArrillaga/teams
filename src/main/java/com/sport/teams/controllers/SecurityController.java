package com.sport.teams.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class SecurityController {
	 @Autowired
	    private UserRepository userRepository;
	 
	 @GetMapping("/prueba")
	 public ResponseEntity<String> prueba(){
		 String username = userRepository.findFirstByOrderByIdAsc()
	                .map(UsuarioApp::getUsername) // Obtiene el username del primer usuario
	                .orElse("No user found"); // Retorna un mensaje si no hay usuarios
	     return new ResponseEntity<String>(username, HttpStatus.OK);
	 }
}
