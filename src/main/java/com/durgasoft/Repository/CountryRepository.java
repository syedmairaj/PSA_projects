package com.durgasoft.Repository;

import com.durgasoft.Entity.Country;
import com.durgasoft.Entity.Property;
import com.durgasoft.Entity.Reviews;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {

 // List<Property> findByCountry(Long countryId);
 Optional<Country> findByCountry(String Country);
  Country findBycountry(String country);
}