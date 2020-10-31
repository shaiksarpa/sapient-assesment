package com.football.standings.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StandingApiDto {
	
	@JsonProperty("country_name")
	private String countryName;
		
	@JsonProperty("league_id")
	private String leagueId;

	@JsonProperty("league_name")
	private String leagueName;
	
	@JsonProperty("team_id")
	private String teamId;
	
	@JsonProperty("team_name")
	private String teamName;
	
	@JsonProperty("overall_league_position")
	private String overallLeaguePosition;

	
	
	
}
