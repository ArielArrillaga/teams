package com.sport.teams.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
//@ControllerAdvice()

public class SecurityController {
	 @GetMapping("/prueba")
	 public ResponseEntity<String> prueba(){

		 return null;
	 }
}
