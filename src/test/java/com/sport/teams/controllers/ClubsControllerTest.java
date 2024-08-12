package com.sport.teams.controllers;

import com.sport.teams.entitys.clubs.Club;
import com.sport.teams.entitys.clubs.ClubRequest;
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
 
  private List<Club> mockList() {
     Club club1 = new Club();
     club1.setNombre("FC Barcelona");
     Club club2 = new Club();
     club2.setNombre("Real Madrid");
    return Arrays.asList(club1, club2);
 }
  
  private Club mockClub() {
	  Club club = new Club();
      club.setNombre("FC Barcelona");
      return club;
  }
  
  @Test
  void testFindAll() {
	  // Arrange
	  List<Club> clubs = this.mockList();
      when(clubsService.findAll()).thenReturn(clubs);
      
	  //Act
      ResponseEntity<List<Club>> response = clubsController.findAll();
      
      //Assert
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals(2, response.getBody().size());
      assertEquals(clubs, response.getBody());
  }
  
  @Test
  public void testGetClubById() {
      // Arrange
      Club club = this.mockClub();
      when(clubsService.findById(1L)).thenReturn(club);

      // Act
      ResponseEntity<Club> response = clubsController.getClubById(1L);

      // Assert
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals(club, response.getBody());
  }

  @Test
  public void testGetClubByNombre() {
     // Arrange
     List<Club> clubs = this.mockList();
     when(clubsService.findByNombre(anyString())).thenReturn(clubs);

     // Act
     ResponseEntity<List<Club>> response = clubsController.getClubByNombre("Barcelona");

     // Assert
     assertEquals(HttpStatus.OK, response.getStatusCode());
     assertEquals(clubs, response.getBody());
  }
  
  @Test
  void testCreateClub() {
      // Arrange
      ClubRequest clubRequest = new ClubRequest();
      clubRequest.setNombre("FC Barcelona");
      Club club = new Club(clubRequest);
      when(clubsService.createClub(clubRequest)).thenReturn(club);

      // Act
      ResponseEntity<Club> response = clubsController.createClub(clubRequest);

      // Assert
      assertEquals(HttpStatus.CREATED, response.getStatusCode());
      assertEquals(club, response.getBody());
  }
  
  @Test
  void testUpdateClub() {
      // Arrange
	  ClubRequest clubRequest = new ClubRequest();
	  clubRequest.setNombre("FC Barcelona");
	  Club updatedClub = new Club();
	  updatedClub.setNombre("FC Barcelona");
      when(clubsService.updateClub(1L, clubRequest)).thenReturn(updatedClub);

      // Act
      ResponseEntity<Club> response = clubsController.updateClub(1L, clubRequest);

      // Assert
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals(updatedClub, response.getBody());
  }
  
  @Test
  void testDeleteClub() {
      // Act
      ResponseEntity<Void> response = clubsController.deleteClub(1L);

      // Assert
      assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }

}

