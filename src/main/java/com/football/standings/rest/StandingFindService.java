package com.football.standings.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.football.standings.client.FootBallApiClient;
import com.football.standings.dto.CountryApiDto;
import com.football.standings.dto.StandingApiDto;
import com.football.standings.dto.StandingFindResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StandingFindService {
	
	@Autowired
	private FootBallApiClient footBallApiClient;
	
	private static List<CountryApiDto> countries;
	
	private final static String OUTPUT_FORMAT = "%s-%s";
	
	@PostConstruct
	public void getCountries() {
		log.info(">>>loading the countries starts here...");
		if(CollectionUtils.isEmpty(countries)) {
			countries = footBallApiClient.getCountries();
		}
		log.info("<<<loading the countries ends here...", countries);
	}
	
	public String findCountryByName(String countryName) {
		log.info(">>>findCountryByName({})", countryName);
		if(!CollectionUtils.isEmpty(countries)) {
			Optional<CountryApiDto> optional = countries.stream().filter(c-> c.getCountryName().equalsIgnoreCase(countryName)).findFirst();
			if(optional.isPresent()) {
				return optional.get().getCountryId();
			}
		}
		return null;
	}
	
	public List<StandingFindResponse> findStandings(Map<String, String> filter) {
		log.info(">>>findStandings({})", filter);
		List<StandingFindResponse> response = new ArrayList<>();
		String country = filter.get("country");
		String team = filter.get("team");
		String league = filter.get("league");
		if(StringUtils.isEmpty(country) && StringUtils.isEmpty(team) && StringUtils.isEmpty(league)) {
			throw new RuntimeException("Invalid input");
		}
		List<StandingApiDto> leagueStandings = footBallApiClient.getLeagueStandings();
		if(!CollectionUtils.isEmpty(leagueStandings)) {
			List<StandingApiDto> filteredList = leagueStandings.stream().filter(filterPredicate(country, team, league)).collect(Collectors.toList());
			if(!CollectionUtils.isEmpty(filteredList)) {
				for (StandingApiDto standingApiDto : filteredList) {
					StandingFindResponse standingFindResponse = new StandingFindResponse();
					standingFindResponse.setCountry(String.format(OUTPUT_FORMAT, findCountryByName(standingApiDto.getCountryName()), standingApiDto.getCountryName()));
					standingFindResponse.setLeague(String.format(OUTPUT_FORMAT, standingApiDto.getLeagueId(), standingApiDto.getLeagueName()));
					standingFindResponse.setTeam(String.format(OUTPUT_FORMAT, standingApiDto.getTeamId(), standingApiDto.getTeamName()));
					standingFindResponse.setOverallLeaguePosition(standingApiDto.getOverallLeaguePosition());
					response.add(standingFindResponse);
				}
			}
		}
		log.info("<<<findStandings({})", filter, response);
		return response;
	}
	
	public static Predicate<StandingApiDto> filterPredicate(String country, String team, String league) {
		if(!StringUtils.isEmpty(country)&& !StringUtils.isEmpty(team) && !StringUtils.isEmpty(league)) {
			return s -> s.getCountryName().equalsIgnoreCase(country) 
					&& s.getLeagueName().equalsIgnoreCase(league) 
					&& s.getTeamName().equalsIgnoreCase(team);
		} else if(!StringUtils.isEmpty(country)&& !StringUtils.isEmpty(league)) {
			return s -> s.getCountryName().equalsIgnoreCase(country) 
					&& s.getLeagueName().equalsIgnoreCase(league);
		} else if(!StringUtils.isEmpty(country)&& !StringUtils.isEmpty(team)) {
			return s -> s.getCountryName().equalsIgnoreCase(country) 
					&& s.getTeamName().equalsIgnoreCase(team);
		} else if(!StringUtils.isEmpty(league)&& !StringUtils.isEmpty(team)) {
			return s -> s.getLeagueName().equalsIgnoreCase(league) 
					&& s.getTeamName().equalsIgnoreCase(team);
		} else if(!StringUtils.isEmpty(country)) {
			return s -> s.getCountryName().equalsIgnoreCase(country);
		} else if(!StringUtils.isEmpty(league)) {
			return s -> s.getLeagueName().equalsIgnoreCase(league);
		} else if(!StringUtils.isEmpty(team)) {
			return s->s.getTeamName().equalsIgnoreCase(team);
		}
		
		return null;
	}
}
