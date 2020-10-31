package com.football.standings.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StandingFindResponse {

	@JsonProperty("Country ID & Name")
	String country;
	
	@JsonProperty("League ID & Name")
	String league;
	
	@JsonProperty("Team ID & Name")
	String team;
	
	@JsonProperty("Overall League Position")
	String overallLeaguePosition;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getOverallLeaguePosition() {
		return overallLeaguePosition;
	}

	public void setOverallLeaguePosition(String overallLeaguePosition) {
		this.overallLeaguePosition = overallLeaguePosition;
	}
	
	
}
