package com.durgasoft.Repository;

import com.durgasoft.Entity.City;
import com.durgasoft.Entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
  Optional<City> findByCity(String City); }