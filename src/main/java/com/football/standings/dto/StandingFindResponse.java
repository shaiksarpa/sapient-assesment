package com.football.standings.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StandingFindResponse {

	@JsonProperty("Country ID & Name")
	String country;
	
	@JsonProperty("League ID & Name")
	String league;
	
	@JsonProperty("Team ID & Name")
	String team;
	
	@JsonProperty("Overall League Position")
	String overallLeaguePosition;

	
}
