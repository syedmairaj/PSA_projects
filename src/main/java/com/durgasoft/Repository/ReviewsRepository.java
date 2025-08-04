package com.durgasoft.Repository;

import com.durgasoft.Entity.Reviews;
import com.durgasoft.Entity.Signup;
import com.durgasoft.payload.ReviewDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {
     List<Reviews> findBySignup(Signup signup);
     Optional<Reviews>findById(Long id);


//     @Query("""
//        SELECT
//          r.id AS id,
//          r.rating AS rating,
//          r.description AS description,
//          p.propertyName AS propertyName,
//          c.city AS city,
//          co.country AS country,
//          s.username AS username,
//          r.date AS date
//        FROM Reviews r
//        JOIN r.property p
//        JOIN p.city c
//        JOIN p.country co
//        JOIN r.signup s
//        WHERE s = :signup
//    """)
//     List<ReviewDto.ReviewProjection> findReviewProjectionsBySignup(@Param("signup") Signup signup);
}