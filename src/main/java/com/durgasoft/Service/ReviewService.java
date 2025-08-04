package com.durgasoft.Service;

import com.durgasoft.Entity.*;
import com.durgasoft.Repository.CountryRepository;
import com.durgasoft.Repository.ReviewsRepository;
import com.durgasoft.Repository.PropertyRepository;
import com.durgasoft.Repository.SignupRepository;
import com.durgasoft.payload.ReviewDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {


    @Autowired
    public ModelMapper mapper;

    private ReviewsRepository reviewsRepository;
    private PropertyRepository propertyRepository;
    private SignupRepository signupRepository;
    private final CountryRepository countryRepository;

    public ReviewService(ReviewsRepository reviewsRepository, PropertyRepository propertyRepository,SignupRepository signupRepository,
                         CountryRepository countryRepository) {
        this.reviewsRepository = reviewsRepository;
        this.propertyRepository = propertyRepository;
        this.signupRepository=signupRepository;
        this.countryRepository = countryRepository;
    }

    //hotelRating
    public ReviewDto hotelRating(Long propertyId, ReviewDto reviewDto, Signup signup) {
        if(reviewDto.getRating()==null || propertyId==null){
            throw new IllegalArgumentException("provide property id and rating");
        }

        //get the property Id from DB
       Property propertyDb = propertyRepository.findById(propertyId).orElseThrow(()-> new IllegalArgumentException("Invalid ID"+   propertyId));

//Build Entity from DTO / DTO to Entity
       // Reviews reviewConv = mapper.map(reviewDto, Reviews.class);

        Reviews review = new Reviews();
        review.setRating(reviewDto.getRating());
        review.setDescription(reviewDto.getDescription());
        review.setProperty(propertyDb);
        review.setSignup(signup);
        review.setDate(LocalDate.now());

        Reviews saveReview = reviewsRepository.save(review);
        //entity to dto
        ReviewDto revDto = mapper.map(saveReview,ReviewDto.class);
//        ReviewDto Dto = new ReviewDto();
//
//        Dto.setRating(saveReview.getRating());
//        Dto.setDescription(saveReview.getDescription());
//        Dto.setId(saveReview.getId());
        revDto.setUsername(signup.getUsername());
        revDto.setCity(propertyDb.getCity().getCity());
        revDto.setCountry(propertyDb.getCountry().getCountry());
//        Dto.setPropertyId(propertyDb.getId());
//        Dto.setPropertyName(propertyDb.getPropertyName());
        revDto.setDate(saveReview.getDate());
        return revDto;

    }
//------------------------------------------user reviews-----------------------------------------
    public List<ReviewDto> userReview(Signup signup) {

   List<Reviews> revDB = reviewsRepository.findBySignup(signup);
    List<ReviewDto> dtoList = new ArrayList<>();

    //Property pro = new Property();
    for(Reviews allDB: revDB){
        ReviewDto revDto = new ReviewDto();
        revDto.setId(allDB.getId());
        revDto.setUsername(allDB.getSignup().getUsername());
        revDto.setRating(allDB.getRating());
        revDto.setDescription(allDB.getDescription());
        revDto.setPropertyName(allDB.getProperty().getPropertyName());
        revDto.setPropertyId(allDB.getProperty().getId());
        revDto.setCity(allDB.getProperty().getCity().getCity());
        revDto.setCountry(allDB.getProperty().getCountry().getCountry());
        revDto.setDate(allDB.getDate());
        dtoList.add(revDto);

    }
return dtoList;
    }


}
