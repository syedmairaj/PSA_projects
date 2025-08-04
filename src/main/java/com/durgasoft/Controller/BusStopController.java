package com.durgasoft.Controller;

import com.durgasoft.Entity.Bus;
import com.durgasoft.Service.BusStopService;
import com.durgasoft.payload.BusDto;
import com.durgasoft.payload.BusStopDto;
import com.durgasoft.payload.StopDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vi/api/hyd")
public class BusStopController {

    private BusStopService busStopService;
    public BusStopController(BusStopService busStopService){
        this.busStopService = busStopService;
    }
//Bus
    @PostMapping("/bus")

    public ResponseEntity<BusDto> addBus(@RequestBody BusDto busDto){
        return new ResponseEntity<>(busStopService.createBus(busDto), HttpStatus.CREATED);
    }

//Stop
    @PostMapping("/stop")
    public ResponseEntity<StopDto> addStop(@RequestBody StopDto stopDto){
        return new ResponseEntity<>(busStopService.addStop(stopDto),HttpStatus.CREATED);
    }

//BusStop
    @PostMapping("/busstop")

    public ResponseEntity<BusStopDto> createBusStop(@RequestBody BusStopDto busStopDto){
        BusStopDto saved = busStopService.createBusStop(busStopDto);
        return new ResponseEntity<>(saved,HttpStatus.CREATED);
    }
}
