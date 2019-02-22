package com.flags.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flags.demo.domain.Continent;

public interface ContinentRepository extends JpaRepository<Continent, Integer> {

	public Optional<Continent> findByName(String pContinentName);
	
	@Query("Select i FROM Continent i JOIN i.countries p WHERE KEY(p) = :countryName")
	public Optional<Continent> findByFlagNameOfCountry(@Param("countryName") String countryName);
	
}
