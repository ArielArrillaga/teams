package com.sport.teams.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sport.teams.anotations.ValidateJwt;
import com.sport.teams.entitys.clubs.Club;
import com.sport.teams.entitys.clubs.ClubRequest;
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
	    return ResponseEntity.ok(clubsService.findAll()); 
	}

	@Override
	@GetMapping("/{id}")
	@ValidateJwt
	public ResponseEntity<Club> getClubById(@PathVariable Long id) {
	    log.info("ClubsController: getClubById: Iniciando servicio, id: " + id);
	    return ResponseEntity.ok(clubsService.findById(id)); 
	}

	@Override
	@GetMapping("/buscar")
	@ValidateJwt
	public ResponseEntity<List<Club>> getClubByNombre(@RequestParam String nombre) {
	    log.info("ClubsController: getClubByNombre: Iniciando servicio, buscando clubes que contengan " + nombre + " en su nombre.");
	    return ResponseEntity.ok(clubsService.findByNombre(nombre)); 
	}

	@Override
	@PostMapping("/")
	@ValidateJwt
	public ResponseEntity<Club> createClub(@RequestBody ClubRequest club) {
	    log.info("ClubsController: createClub: Iniciando servicio, creando club " + club.getNombre());
	    return ResponseEntity.status(HttpStatus.CREATED).body(clubsService.createClub(club)); 
	}

	@Override
	@PutMapping("/{id}")
	@ValidateJwt
	public ResponseEntity<Club> updateClub(@PathVariable Long id, @RequestBody ClubRequest club) {
	    log.info("ClubsController: updateClub: Iniciando servicio, modificando club con id: " + id);
	    return ResponseEntity.ok(clubsService.updateClub(id, club)); 
	}

	@Override
	@DeleteMapping("/{id}")
	@ValidateJwt
	public ResponseEntity<Void> deleteClub(@PathVariable Long id) {
	    log.info("ClubsController: deleteClub: Iniciando servicio, id: " + id);
	    clubsService.deleteClub(id);
	    return ResponseEntity.noContent().build(); 
	}

}
