package com.durgasoft.Repository;

import com.durgasoft.Entity.Country;
import com.durgasoft.Entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Long> {

  //JPQL to search property based on city and country
// p is alias for property and c is alias for city
  //below query is use to search properties in the city.
 @Query("Select p from Property p join p.city c where c.city=:city")
  List<Property> searchProperty(
          @Param("city") String city);
 List<Property>findByCity(Long cityId);
 List<Property>findByCountry(Long countryId);
Optional<Property>findById(Long propertyId);
    List<Property>findBycountry(Country country);

}