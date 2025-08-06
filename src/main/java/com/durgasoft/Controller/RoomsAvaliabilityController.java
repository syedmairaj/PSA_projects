package com.durgasoft.Controller;

import com.durgasoft.Service.RoomsAvailabilityService;
import com.durgasoft.payload.RoomsAvaliabilityDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/vi/api/rooms")
public class RoomsAvaliabilityController {



    private RoomsAvailabilityService roomsAvailabilityService;

    public RoomsAvaliabilityController(RoomsAvailabilityService roomsAvailabilityService) {
        this.roomsAvailabilityService = roomsAvailabilityService;
    }

    @PostMapping("/addroom/property/{propertyId}")

    public ResponseEntity<?> addRooms(@RequestBody RoomsAvaliabilityDto roomsdto, @PathVariable Long propertyId){

        String addingRoom = roomsAvailabilityService.addRooms(roomsdto,propertyId);
        return new ResponseEntity<>(addingRoom,HttpStatus.CREATED);
    }


    //Search rooms by from date and to date
    //use below url to search from postman
    //http://localhost:8081/vi/api/rooms/search/3?fromDate=2025-08-04&toDate=2025-08-08&room_type=delux
    @GetMapping("/search/{propertyId}")

    public ResponseEntity<?> searchRooms(@PathVariable long propertyId, RoomsAvaliabilityDto dto,
                                                  @RequestParam LocalDate fromDate, @RequestParam LocalDate toDate, @RequestParam String room_type){

        return roomsAvailabilityService.searchRooms(propertyId,dto,fromDate,toDate,room_type);
       // return new ResponseEntity<>(searchHotelRooms,HttpStatus.OK);
    }

}
