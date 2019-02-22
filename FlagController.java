package com.flags.demo.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.flags.demo.core.ContinentNotFoundException;
import com.flags.demo.core.FlagNotFoundException;
import com.flags.demo.domain.Continent;
import com.flags.demo.service.FlagPickerService;

@RestController()
public class FlagController {
	@Autowired
	private FlagPickerService flagPickerService;
	
	@RequestMapping("/flags/continent/all")
	public List<Continent> getAllFlags() {
		return flagPickerService.getAllFlagData();
	}
	
	@RequestMapping("/flags/continent/{continentName}")
	public Continent getAllFlags(@PathVariable String continentName ) {
		Optional<Continent> result = flagPickerService.getFlagsByContinentName(continentName);
		if(result.isPresent())
			return result.get();
		else
			throw new ContinentNotFoundException();
	}
	
	@RequestMapping("/flags/continent/{continentName}/{countrytName}")
	public @ResponseBody String getFlagByCountryAndContinentName(@PathVariable String continentName, @PathVariable String countrytName) {
		String flagName = flagPickerService.getFlagByContinentAndCountryName(continentName, countrytName);
		if(!"".equalsIgnoreCase(flagName))
		 return flagName;
		else
			throw new FlagNotFoundException();
	}
	@RequestMapping("/flags/country/{countrytName}")
	public @ResponseBody String getFlagByCountryName(@PathVariable String countrytName) {
		String flagName = flagPickerService.getFlagByCountryName(countrytName);
		if(!"".equalsIgnoreCase(flagName))
			 return flagName;
			else
				throw new FlagNotFoundException();
	}
}

