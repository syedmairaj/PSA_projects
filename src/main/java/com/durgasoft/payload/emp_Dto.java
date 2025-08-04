package com.durgasoft.payload;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class emp_Dto {

    private Long id;

    private String ename;

    @Size(min =10, max=10, message="size must be 10")
    private String emobile;

    @Email//(message = "email must be valid", regexp = "^[A-Za-z0-9._%+-]+@example\\.com$")
    private String eemail;

    private float esal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEmobile() {
        return emobile;
    }

    public void setEmobile(String emobile) {
        this.emobile = emobile;
    }

    public String getEemail() {
        return eemail;
    }

    public void setEemail(String eemail) {
        this.eemail = eemail;
    }

    public float getEsal() {
        return esal;
    }

    public void setEsal(float esal) {
        this.esal = esal;
    }
}
