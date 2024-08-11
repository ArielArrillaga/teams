package com.sport.teams.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sport.teams.entitys.clubs.Club;

@Repository
public interface ClubsRepository extends JpaRepository<Club, Long> {
	
    //busca los clubes que coincidan con la busqueda
	List<Club> findByNombreContaining(String nombre);
}