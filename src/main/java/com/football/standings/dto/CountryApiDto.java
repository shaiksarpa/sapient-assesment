package com.football.standings.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CountryApiDto {
	
	@JsonProperty("country_id")
	private String countryId;
	
	@JsonProperty("country_name")
	private String countryName;

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	
}
