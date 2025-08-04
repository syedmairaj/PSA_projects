package com.durgasoft.Service;

import com.durgasoft.Entity.City;
import com.durgasoft.Entity.Country;
import com.durgasoft.Entity.Property;
import com.durgasoft.Exception.ResourceNotFound;
import com.durgasoft.Repository.CityRepository;
import com.durgasoft.Repository.CountryRepository;
import com.durgasoft.Repository.PropertyRepository;
import com.durgasoft.payload.PropertyDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

    @Autowired
    private ModelMapper mapper;

    private PropertyRepository propertyRepository;
    private CountryRepository countryRepository;
    private CityRepository cityRepository;

    public PropertyService(PropertyRepository propertyRepository, CountryRepository countryRepository, CityRepository cityRepository) {
        this.propertyRepository = propertyRepository;
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
    }

    public Property addProperty(PropertyDto dto) {



           Property property = new Property();
            property.setPropertyName(dto.getPropertyName());
            property.setNumberOfGuests(dto.getNumberOfGuests());
            property.setNumberOfBedrooms(dto.getNumberOfBedrooms());
            property.setNumberOfBathrooms(dto.getNumberOfBathrooms());

// Find or create city
        City city = cityRepository.findByCity(dto.getCity())
                .orElseGet(() -> {
                    City newCity = new City();
                    newCity.setCity(dto.getCity());
                    return cityRepository.saveAndFlush(newCity);
                });

        // Find or create country
        Country country = countryRepository.findByCountry(dto.getCountry())
                .orElseGet(() -> {
                    Country newCountry = new Country();
                    newCountry.setCountry(dto.getCountry());
                    return countryRepository.saveAndFlush(newCountry);
                });
        property.setCity(city);
        property.setCountry(country);
            return propertyRepository.save(property);
        }
    }


















