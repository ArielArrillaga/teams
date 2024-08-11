package com.sport.teams.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sport.teams.entitys.clubs.Club;

@Repository
public interface ClubsRepository extends JpaRepository<Club, Long> {
}