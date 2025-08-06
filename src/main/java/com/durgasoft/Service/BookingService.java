package com.durgasoft.Service;

import com.durgasoft.Entity.Bookings;
import com.durgasoft.Entity.Property;
import com.durgasoft.Entity.RoomsAvaliability;
import com.durgasoft.Repository.BookingsRepository;
import com.durgasoft.Repository.PropertyRepository;
import com.durgasoft.Repository.RoomsAvaliabilityRepository;
import com.durgasoft.payload.BookingsDto;
import com.durgasoft.payload.RoomsAvaliabilityDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private ModelMapper mapper;

    private RoomsAvaliabilityRepository roomsAvaliabilityRepository;
    private BookingsRepository bookingsRepository;
    private PropertyRepository propertyRepository;
    private PDFGenerator pdfGenerator;



    public BookingService(PDFGenerator pdfGenerator,RoomsAvaliabilityRepository roomsAvaliabilityRepository, BookingsRepository bookingsRepository,PropertyRepository propertyRepository) {
        this.roomsAvaliabilityRepository = roomsAvaliabilityRepository;
        this.bookingsRepository = bookingsRepository;
        this.propertyRepository = propertyRepository;
        this.pdfGenerator =pdfGenerator;
    }

    public ResponseEntity<?> bookRoom(BookingsDto dto) throws IOException {
        if(dto.getPropertyId()==null || dto.getMobile() ==null){
            throw new IllegalArgumentException("select property and mobile filed cannot be blank");
        }
      Property property =propertyRepository.findById(dto.getPropertyId()).orElseThrow(()-> new IllegalArgumentException("cannot find property"));

       RoomsAvaliability roomsAvaliability = roomsAvaliabilityRepository.findByDateAndRoomType(dto.getPropertyId(), dto.getRoomtype(),dto.getFromDate()).orElseThrow(()-> new IllegalArgumentException("no rooms available"));
    if(roomsAvaliability.getTotalrooms() <=0){
        throw new IllegalArgumentException("fully booked");
    }
        roomsAvaliability.setTotalrooms(roomsAvaliability.getTotalrooms()-1);
//.isBlank check the blank and ""
        if(dto.getBookingstatus() ==null || dto.getBookingstatus().isBlank()) {
            throw new IllegalArgumentException("check the dates and rooms availability");
        }
        Bookings bookings = new Bookings();
        bookings.setGuestname(dto.getGuestname());
        bookings.setEmail(dto.getEmail());
        bookings.setBooking_status(dto.getBookingstatus());
        bookings.setProperty(property);
        bookings.setRoomsAvaliability(roomsAvaliability);
        bookings.setMobile(dto.getMobile());
       // bookings.setPdf_invoice("sent an email");

        Bookings book1 = bookingsRepository.save(bookings);
        pdfGenerator.generatePDFInvoice(book1);

        //Entity to Dto manual conversation
        BookingsDto bookingsDto = new BookingsDto();
        bookingsDto.setId(book1.getId());;
        bookingsDto.setPropertyId(property.getId());
        bookingsDto.setGuestname(book1.getGuestname());
        bookingsDto.setEmail(book1.getEmail());;
        bookingsDto.setMobile(bookings.getMobile());
        bookingsDto.setBookingstatus(book1.getBooking_status());
        bookingsDto.setFromDate(roomsAvaliability.getDate());
        bookingsDto.setPdfinvoice(book1.getPdf_invoice());
        bookingsDto.setRoomtype(roomsAvaliability.getRoomtype());
        bookingsDto.setPrice(roomsAvaliability.getPrice());
       // BookingsDto dtosaved = mapper.map(book1, BookingsDto.class);

        return new ResponseEntity<>(bookingsDto, HttpStatus.OK);
        }


}
