package com.football.standings.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.football.standings.dto.CountryApiDto;
import com.football.standings.dto.StandingApiDto;


@Component
public class FootBallApiClient {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${football.api.endpoint}")
	private String api;
	
	@Value("${football.api.getStandings.leagueId}")
	private String leagueId;
	
	@Value("${football.api.key}")
	private String apiKey;
	
	public List<StandingApiDto> getLeagueStandings() {
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(api)
				.queryParam("action", "get_standings").queryParam("league_id", leagueId).queryParam("APIkey", apiKey);
		ResponseEntity<StandingApiDto[]> responseEntity = restTemplate.getForEntity(uriComponentsBuilder.toUriString(), StandingApiDto[].class);
		if(responseEntity.hasBody() && responseEntity.getStatusCodeValue() == 200) {
			return Arrays.asList(responseEntity.getBody());
		}	 
		return new ArrayList<StandingApiDto>();	
	}
	
	public List<CountryApiDto> getCountries() {
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(api)
				.queryParam("action", "get_countries").queryParam("APIkey", apiKey);
		ResponseEntity<CountryApiDto[]> responseEntity = restTemplate.getForEntity(uriComponentsBuilder.toUriString(), CountryApiDto[].class);
		if(responseEntity.hasBody() && responseEntity.getStatusCodeValue() == 200) {
			return Arrays.asList(responseEntity.getBody());
		}	 
		return new ArrayList<CountryApiDto>();	
	}
}
