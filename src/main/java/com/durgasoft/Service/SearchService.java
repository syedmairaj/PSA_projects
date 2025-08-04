package com.durgasoft.Service;

import com.durgasoft.Entity.Country;
import com.durgasoft.Entity.Property;
import com.durgasoft.Entity.Reviews;
import com.durgasoft.Repository.CountryRepository;
import com.durgasoft.Repository.PropertyRepository;
import com.durgasoft.Repository.ReviewsRepository;
import com.durgasoft.payload.SearchDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SearchService {




private CountryRepository countryRepository;
private PropertyRepository propertyRepository;
private ReviewsRepository reviewsRepository;

    public SearchService(CountryRepository countryRepository, PropertyRepository propertyRepository, ReviewsRepository reviewsRepository) {
        this.countryRepository = countryRepository;
        this.propertyRepository = propertyRepository;
        this.reviewsRepository = reviewsRepository;
    }

    public List<SearchDto> searchByCountry(String country) {

        Country country1 = countryRepository.findBycountry(country);
        List<Property> countryDB = propertyRepository.findBycountry(country1);

        List<SearchDto> list_Country = new ArrayList<>();
        SearchDto searchDto = new SearchDto();
        for (Property x : countryDB) {
            List<Reviews> rev = x.getReviews();
            if (rev != null && !rev.isEmpty()) {
                for (Reviews i : rev) {

                    searchDto.setId(x.getId());
                    searchDto.setCountry(x.getCountry().getCountry());
                    searchDto.setCity(x.getCity().getCity());
                    searchDto.setPropertyName(x.getPropertyName());
                    searchDto.setNumberOfBedrooms(x.getNumberOfBedrooms());
                    searchDto.setNumberOfBathrooms(x.getNumberOfBathrooms());
                    searchDto.setNumberOfGuests(x.getNumberOfGuests());
                    searchDto.setRatings(i.getRating());
                    searchDto.setDescription(i.getDescription());
                    list_Country.add(searchDto);
                }
            }else {

                searchDto.setId(x.getId());
                searchDto.setCountry(x.getCountry().getCountry());
                searchDto.setCity(x.getCity().getCity());
                searchDto.setPropertyName(x.getPropertyName());
                searchDto.setNumberOfBedrooms(x.getNumberOfBedrooms());
                searchDto.setNumberOfBathrooms(x.getNumberOfBathrooms());
                searchDto.setNumberOfGuests(x.getNumberOfGuests());
                list_Country.add(searchDto);
            }





        }
        return list_Country;

    }
}
