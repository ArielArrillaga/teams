package com.sport.teams.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sport.teams.anotations.ValidateJwt;
import com.sport.teams.entitys.clubs.Club;
import com.sport.teams.services.IClubsService;

@RestController
@RequestMapping("/equipos")
public class ClubsController implements IClubsController {
	private static final Logger log = LoggerFactory.getLogger(ClubsController.class);

	@Autowired
	IClubsService clubsService;
	
	@Override
	@GetMapping("/")
	@ValidateJwt
	public ResponseEntity<List<Club>> findAll() {
		log.info("ClubsController: findAll: Iniciando servicio.");
		return new ResponseEntity<List<Club>> (clubsService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Club> getClubById(@PathVariable Long id) {
		log.info("ClubsController: getClubById: Iniciando servicio, id: "+id);
        return new ResponseEntity<Club> (clubsService.findById(id), HttpStatus.OK);
    }

}
