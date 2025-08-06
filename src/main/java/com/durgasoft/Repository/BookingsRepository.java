package com.durgasoft.Repository;

import com.durgasoft.Entity.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookingsRepository extends JpaRepository<Bookings, Long> {

}