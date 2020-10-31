package com.football.standings.integration;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import com.football.standings.StandingsApplication;
import com.football.standings.dto.StandingFindResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StandingsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StandingRestTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;
	
	private String getRootUrl() {
		return "http://localhost:"+port;
	}
	
	@Test
	public void contextload() {
		assertNotNull(restTemplate);
	}
	
	@Test
	public void testGetStandings() {
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(getRootUrl()+"/football/standings/")
				.queryParam("country", "England").queryParam("team", "Liverpool").queryParam("league","Premier League");
		ResponseEntity<StandingFindResponse[]> responseEntity = restTemplate.getForEntity(uriComponentsBuilder.toUriString(), StandingFindResponse[].class);
		assertNotNull(responseEntity);
		assertEquals(responseEntity.getStatusCodeValue(), 200);
		assertTrue(responseEntity.hasBody());
	}
}
