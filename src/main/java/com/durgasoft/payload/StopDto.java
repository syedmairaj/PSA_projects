package com.durgasoft.payload;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class StopDto {

     //private Long id;
     private String stopname;
    private Long stopid;
//    public Long getId() {
//        return id;
//    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getStopname() {
        return stopname;
    }

    public void setStopname(String stopname) {
        this.stopname = stopname;
    }

    public Long getStopid() {
        return stopid;
    }

    public void setStopid(Long stopid) {
        this.stopid = stopid;
    }
}
