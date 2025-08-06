package com.durgasoft.payload;

import com.durgasoft.Entity.Property;
import com.durgasoft.Entity.RoomsAvaliability;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

public class BookingsDto {

    private Long id;
    private String guestname;
    private String email;
    private String mobile;
    private Long propertyId;
    private LocalDate fromDate;
   // private LocalDate toDate;
    private String roomtype;
    private Long price;
    private String bookingstatus;
    private String pdfinvoice;
  //  private RoomsAvaliabilityDto roomsdata;
 //   private RoomsAvaliability roomsAvaliability;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGuestname() {
        return guestname;
    }

    public void setGuestname(String guestname) {
        this.guestname = guestname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public String getBookingstatus() {
        return bookingstatus;
    }

    public void setBookingstatus(String bookingstatus) {
        this.bookingstatus = bookingstatus;
    }

    public String getPdfinvoice() {
        return pdfinvoice;
    }

    public void setPdfinvoice(String pdfinvoice) {
        this.pdfinvoice = pdfinvoice;
    }

//    public RoomsAvaliabilityDto getRoomsdata() {
//        return roomsdata;
//    }
//
//    public void setRoomsdata(RoomsAvaliabilityDto roomsdata) {
//        this.roomsdata = roomsdata;
//    }

//    public RoomsAvaliability getRoomsAvaliability() {
//        return roomsAvaliability;
//    }
//
//    public void setRoomsAvaliability(RoomsAvaliability roomsAvaliability) {
//        this.roomsAvaliability = roomsAvaliability;
//    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }


    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
