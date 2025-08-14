package com.durgasoft.payload;

import com.durgasoft.Entity.City;
import com.durgasoft.Entity.Country;
import com.durgasoft.Entity.Property;
import com.durgasoft.Entity.Reviews;

import java.util.List;

public class SearchByPriceDto {

    private String country;
    private City city;
    private String propertyName;
    private Long price;
    private String roomType;
    private int rooms;
    private int bathrooms;




    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }
}
