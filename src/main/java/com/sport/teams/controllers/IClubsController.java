package com.sport.teams.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.sport.teams.entitys.clubs.Club;
import com.sport.teams.entitys.clubs.ClubRequest;

import jakarta.validation.Valid;

public interface IClubsController {
	public ResponseEntity<List<Club>> findAll();
	public ResponseEntity<Club> getClubById(Long id);
	public ResponseEntity<List<Club>> getClubsByNombre(String nombre);
	public ResponseEntity<Club> createClub(ClubRequest club);

}
