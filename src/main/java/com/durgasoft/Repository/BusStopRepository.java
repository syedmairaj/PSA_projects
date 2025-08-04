package com.durgasoft.Repository;

import com.durgasoft.Entity.Bus;
import com.durgasoft.Entity.BusStop;
import com.durgasoft.Entity.Stop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusStopRepository extends JpaRepository<BusStop, Long> {
  //  Optional<BusStop> findBybusandStop(Bus bus, Stop stop);
}