package com.durgasoft.Service;


import com.durgasoft.Entity.City;
import com.durgasoft.Entity.Property;
import com.durgasoft.Entity.Reviews;
import com.durgasoft.Entity.RoomsAvaliability;
import com.durgasoft.Repository.PropertyRepository;
import com.durgasoft.Repository.RoomsAvaliabilityRepository;
import com.durgasoft.payload.ReviewDto;
import com.durgasoft.payload.RoomsAvaliabilityDto;
import com.durgasoft.payload.SearchByPriceDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomsAvailabilityService {

    @Autowired
    private ModelMapper mapper;

    private RoomsAvaliabilityRepository roomsrepo;
    private PropertyRepository propertyRepository;

    public RoomsAvailabilityService(RoomsAvaliabilityRepository roomsrepo, PropertyRepository propertyRepository) {
        this.roomsrepo = roomsrepo;
        this.propertyRepository = propertyRepository;
    }


    public String addRooms(RoomsAvaliabilityDto roomsdto, Long propertyId) {
        if(roomsdto ==null || propertyId ==null){
            throw new IllegalArgumentException("fields are empty");
        }

        Optional<Property> property = propertyRepository.findById(propertyId);
        if(property.isEmpty()){
            throw new IllegalArgumentException("cannot find property Id");
        }
        //Dto to Entity
        RoomsAvaliability roomData = mapper.map(roomsdto,RoomsAvaliability.class);

        Property property1 = property.get();
        roomData.setProperty(property1);
        RoomsAvaliability savedRooms = roomsrepo.save(roomData);
        //Entity to Dto
        RoomsAvaliabilityDto roomsDto = mapper.map(savedRooms,RoomsAvaliabilityDto.class);
        return "rooms data saved";
    }

    //Search rooms by to and from date
    public ResponseEntity<?> searchRooms(long propertyId, RoomsAvaliabilityDto dto, LocalDate fromDate, LocalDate toDate, String roomType) {
        if (dto.getPropertyId() ==null) {
            throw new IllegalArgumentException("fields are empty");
        }
        //Dto to Entity
        RoomsAvaliability roomData = mapper.map(dto, RoomsAvaliability.class);
        Optional<Property> property =propertyRepository.findById(propertyId);
        if(property.isEmpty()){
            throw new IllegalArgumentException("cannot find property");
        }
        Property property1 = property.get();
        City city = property1.getCity();
        List<RoomsAvaliability> rooms = roomsrepo.searchByRooms(
                fromDate,toDate,roomType);
//        if(!dto.getDate().equals(toDate)){
//            throw new IllegalArgumentException("room is not available for this date");
//        }
        //Entity to Dto
        // below stream uses to convert from entity to do and filter city coz this city filed is not there in roomsavaliabiitlty table, this allows you to process each element using functional-style code (map, filter, etc.).
        List<RoomsAvaliabilityDto> roomsDtos = rooms.stream().map(room-> {
                    RoomsAvaliabilityDto dto1 = mapper.map(room, RoomsAvaliabilityDto.class);
                    dto1.setCity(city);
                    dto1.setPropertyId(propertyId);
                    return dto1;
                }).toList();

        return new ResponseEntity<>(roomsDtos, HttpStatus.OK);



    }


//search by price

    public ResponseEntity<?> searchByPrice(Long price) {

        if (price == null || price <= 0) {
            throw new IllegalArgumentException("price is required or price cannot be less than 0");
        }
        List<RoomsAvaliability> findPrice = roomsrepo.searchByPrice(price);
        if(findPrice.isEmpty()){
            throw new IllegalArgumentException("cannot find hotel for this price");
        }
//create new list and the collection framework to map each price and pull the data accordingly 
        List<SearchByPriceDto> result = findPrice.stream().map(room-> {
            SearchByPriceDto data1 = new SearchByPriceDto();
           data1.setPrice(room.getPrice());
           data1.setRoomType(room.getRoomtype());
           Property property = room.getProperty();
           if(property!=null) {
               data1.setPropertyName(property.getPropertyName());
               data1.setCountry(property.getCountry().getCountry());
               data1.setRooms(property.getNumberOfBedrooms());
               data1.setBathrooms(property.getNumberOfBathrooms());
               data1.setCity(property.getCity());

           }
                return  data1;

                   }).collect(Collectors.toList());

            return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
