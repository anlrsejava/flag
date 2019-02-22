package com.flags.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flags.demo.dao.ContinentRepository;
import com.flags.demo.domain.Continent;
import com.flags.demo.util.EmojiUtil;

@Service
public class FlagPickerService {
	@Autowired
	private ContinentRepository continentRepository;
	
	public List<Continent>  getAllFlagData() {
		return continentRepository.findAll();
	}
	public Optional<Continent> getFlagsByContinentName(String pContinentName) {
		return continentRepository.findByName(pContinentName);
	}
	public String getFlagByContinentAndCountryName(String pContinentName, String pCountryName) {
		Optional<Continent> result = continentRepository.findByName(pContinentName);
		if(result.isPresent() && result.get().getCountries() != null && !result.get().getCountries().isEmpty())
			return EmojiUtil.getEmoji(result.get().getCountries().get(pCountryName));
		else
			return "";
	}
	
	public String getFlagByCountryName(String pCountryName) {
		Optional<Continent> result = continentRepository.findByFlagNameOfCountry(pCountryName);
		if(result.isPresent() && result.get().getCountries() != null && !result.get().getCountries().isEmpty()) {
			return EmojiUtil.getEmoji(result.get().getCountries().get(pCountryName));
		}
		else
			return "";
		
	}
	
}
