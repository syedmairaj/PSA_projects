package com.durgasoft.Controller;

import com.durgasoft.Entity.Country;
import com.durgasoft.Entity.Reviews;
import com.durgasoft.Entity.Signup;
import com.durgasoft.Service.ReviewService;
import com.durgasoft.payload.ReviewDto;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/vi/api/reviews/")
public class ReviewsController {

private ReviewService reviewService;

    public ReviewsController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("rating")

    public ResponseEntity<?> hotelRating(@RequestParam Long propertyId ,@RequestBody ReviewDto reviewDto,
                                         @AuthenticationPrincipal Signup signup){
        //Long propertyId = reviewDto.getProperty().getId();
        ReviewDto contData = reviewService.hotelRating(propertyId,reviewDto,signup);
        return new ResponseEntity<>(contData, HttpStatus.OK);
    }

    // get reviews from logged in users, this code provide the list of reviews from logged in user,

    @GetMapping("user/review")
    public List<ReviewDto> userReview(@AuthenticationPrincipal Signup signup){
       return reviewService.userReview(signup);
          //  return new ResponseEntity<>(dtoData,HttpStatus.OK);
        }


}
