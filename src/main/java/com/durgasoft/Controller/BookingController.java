package com.durgasoft.Controller;

import com.durgasoft.Service.BookingService;
import com.durgasoft.Service.RoomsAvailabilityService;
import com.durgasoft.payload.BookingsDto;
import com.durgasoft.payload.RoomsAvaliabilityDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/vi/api/rooms/")
public class BookingController {

private BookingService bookingService;
private RoomsAvailabilityService roomsAvailabilityService;

    public BookingController(BookingService bookingService, RoomsAvailabilityService roomsAvailabilityService) {
        this.bookingService = bookingService;
        this.roomsAvailabilityService = roomsAvailabilityService;
    }
    //this get the logs
  //  private static final Logger log = LoggerFactory.getLogger(BookingController.class);
    @PostMapping("booking")

    public ResponseEntity<?> bookRoom(@RequestBody BookingsDto dto) throws IOException {
        return new ResponseEntity<>(bookingService.bookRoom(dto),HttpStatus.OK);



    }

}
