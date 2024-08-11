package com.sport.teams.controllers;

import com.sport.teams.entitys.clubs.Club;
import com.sport.teams.services.ClubsService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig
public class ClubsControllerTest {
 @Mock
 private ClubsService clubsService;

 @InjectMocks
 private ClubsController clubsController;

 public ClubsControllerTest() {
     MockitoAnnotations.openMocks(this);
 }

 @Test
 public void testGetClubByNombre() {
     // Arrange
     Club club1 = new Club();
     club1.setNombre("FC Barcelona");
     Club club2 = new Club();
     club2.setNombre("Real Madrid");
     List<Club> clubs = Arrays.asList(club1, club2);
     when(clubsService.findByNombre(anyString())).thenReturn(clubs);

     // Act
     ResponseEntity<List<Club>> response = clubsController.getClubByNombre("Barcelona");

     // Assert
     assertEquals(HttpStatus.OK, response.getStatusCode());
     assertEquals(clubs, response.getBody());
 }
}

