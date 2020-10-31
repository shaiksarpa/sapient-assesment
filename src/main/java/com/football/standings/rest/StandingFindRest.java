package com.football.standings.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.football.standings.dto.StandingFindResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/football/standings/")
@Slf4j
public class StandingFindRest {

	@Autowired
	private StandingFindService standingFindService;
	
	@GetMapping
	public List<StandingFindResponse> get(@RequestParam Map<String, String> searchParams) {
		log.info(">>>getStandingsBy", searchParams);
		return standingFindService.findStandings(searchParams);
	}
}
