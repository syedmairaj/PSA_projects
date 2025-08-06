package com.durgasoft.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@Entity
@Table(name = "bookings")
public class Bookings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "guest_name", nullable = false)
    private String guestname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "mobile", nullable = false)
    private String mobile;


    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @ManyToOne
    @JoinColumn(name = "rooms_avaliability_id")
    private RoomsAvaliability roomsAvaliability;

    @Column(name = "booking_status")
    private String booking_status;

    @Column(name = "pdf_invoice")
    private String pdf_invoice;


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

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public String getBooking_status() {
        return booking_status;
    }

    public void setBooking_status(String booking_status) {
        this.booking_status = booking_status;
    }

    public String getPdf_invoice() {
        return pdf_invoice;
    }

    public void setPdf_invoice(String pdf_invoice) {
        this.pdf_invoice = pdf_invoice;
    }

    public RoomsAvaliability getRoomsAvaliability() {
        return roomsAvaliability;
    }

    public void setRoomsAvaliability(RoomsAvaliability roomsAvaliability) {
        this.roomsAvaliability = roomsAvaliability;
    }
}