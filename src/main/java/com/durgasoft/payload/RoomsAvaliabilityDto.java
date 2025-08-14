package com.durgasoft.payload;

import com.durgasoft.Entity.City;
import com.durgasoft.Entity.Property;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

public class RoomsAvaliabilityDto {


    private Long id;
    private String roomtype;
    private Integer totalrooms;
    private Long price;
    private LocalDate date;
    private Long propertyId;
    private City city;


    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Long getPropertyId() {
        return propertyId;
    }



    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public Integer getTotalrooms() {
        return totalrooms;
    }

    public void setTotalrooms(Integer totalrooms) {
        this.totalrooms = totalrooms;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


}
