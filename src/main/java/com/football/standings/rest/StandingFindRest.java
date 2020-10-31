package com.football.standings.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.football.standings.dto.StandingFindResponse;

@RestController
@RequestMapping("/football/standings/")
public class StandingFindRest {

	@Autowired
	private StandingFindService standingFindService;
	
	@GetMapping
	public List<StandingFindResponse> get(@RequestParam Map<String, String> searchParams) {
		return standingFindService.findStandings(searchParams);
	}
}
