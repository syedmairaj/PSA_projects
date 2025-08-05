package com.durgasoft.Repository;

import com.durgasoft.Entity.Property;
import com.durgasoft.Entity.RoomsAvaliability;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.time.LocalDate;
import java.util.List;


public interface RoomsAvaliabilityRepository extends JpaRepository<RoomsAvaliability, Long> {
//JQL query
    @Query("Select r from RoomsAvaliability r  where r.date >= :fromDate AND r.date <=:toDate AND r.roomtype = :roomtype " )
List<RoomsAvaliability>searchByRooms(
        @Param("fromDate")LocalDate fromDate,
        @Param("toDate") LocalDate toDate,
        @Param("roomtype") String roomtype);
}

// @Query("Select r from RoomsAvaliability r  where r.roomtype = :roomtype   AND r.date between :fromDate AND :toDate" )