package com.durgasoft.Service;

import com.durgasoft.Entity.Bus;
import com.durgasoft.Entity.BusStop;
import com.durgasoft.Entity.Stop;
import com.durgasoft.Repository.BusRepository;
import com.durgasoft.Repository.BusStopRepository;
import com.durgasoft.Repository.StopRepository;
import com.durgasoft.payload.BusDto;
import com.durgasoft.payload.BusStopDto;
import com.durgasoft.payload.StopDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BusStopService {


    @Autowired
    private ModelMapper mapper;

    private BusRepository busRepository;
    private StopRepository stopRepository;
    private BusStopRepository busStopRepository;

    public BusStopService(BusRepository busRepository, StopRepository stopRepository, BusStopRepository busStopRepository) {
        this.busRepository = busRepository;
        this.stopRepository = stopRepository;
        this.busStopRepository = busStopRepository;
    }


//Bus
    public BusDto createBus(BusDto busDto) {
        Bus addbus = BusDtoToEntity(busDto);
        Bus saveBus = busRepository.save(addbus);
        BusDto dtoData = EntityToBusDto(saveBus);
        return  dtoData;

    }
//Stop
public StopDto addStop(StopDto stopDto) {
        Stop addStop = stopToEntity(stopDto);
        Stop saveStop = stopRepository.save(addStop);
        StopDto stopdto = entityToStop(saveStop);
        return stopDto;
}

//busStop
public BusStopDto createBusStop(BusStopDto busStopDto) {
       //check if bus exists

     Bus bus = busRepository.findById(busStopDto.getBusid()).orElseThrow(()-> new RuntimeException("Bus Not found with ID:" +busStopDto.getId()));
        //check if stop exits
    Stop stop =stopRepository.findById(busStopDto.getStopid()).orElseThrow(() ->new RuntimeException("Stop not found with ID:" + busStopDto.getId()));

    BusStop busstop = new BusStop();
    busstop.setBus(bus);
    busstop.setStop(stop);
    busstop.setOrder_number(busStopDto.getOrder_number());
    busstop.setDeparture_time(busStopDto.getDeparture_time());
    BusStop busStopsaaved = busStopRepository.save(busstop);

    //return DTO
    BusStopDto result = new BusStopDto();
    result.setId(busStopsaaved.getId());
    result.setId(busStopsaaved.getBus().getBusid());
    result.setId(busStopsaaved.getStop().getStopid());
    result.setDeparture_time(busStopsaaved.getDeparture_time());
    result.setOrder_number(busStopsaaved.getOrder_number());
    return result;
}


    //BusDtoToEntity

    public Bus BusDtoToEntity(BusDto busDto){
        return mapper.map(busDto,Bus.class);
    }
    //EntityToBusDto

    public BusDto EntityToBusDto(Bus bus){
        return mapper.map(bus,BusDto.class);
    }

    //StoptoEntity

    public Stop stopToEntity(StopDto stopDto){
        return mapper.map(stopDto,Stop.class);
    }

    public StopDto entityToStop(Stop stop){
        return mapper.map(stop,StopDto.class);
    }

    //BusStopToEntity

    public BusStop BusStopEntity(BusStopDto busStopDto){
        return mapper.map(busStopDto,BusStop.class);
    }

    //EntityToBusStop

    public BusStopDto EntityToBusStop(BusStop busStop){
        return mapper.map(busStop,BusStopDto.class);
    }

}
