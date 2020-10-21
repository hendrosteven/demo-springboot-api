package com.brainmatics.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class KaryawanDto {

    private int id;

    @NotEmpty(message = "Nip is required")
    @Size(min = 3, max = 5, message="Nip length must be 3 to 5 characters")
    @Pattern(regexp="NP[0-9]+", message = "Nip must be start with NP")
    private String nip;
    
    @NotEmpty(message = "First Name is required")
    private String firstName;

    private String lastName;

    @NotEmpty(message = "Address is required")
    private String address;

    @NotEmpty(message = "Phone Number is required")
    private String phoneNumber;

    @Email(message="Invalid email address format")
    private String emailAddress;

    private int departemenId;

    public KaryawanDto() {

    }

    public KaryawanDto(String nip, String firstName, String lastName, String address, String phoneNumber, String email, int departemenId){
        this.nip = nip;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = email;
        this.departemenId = departemenId;
    }

    

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepartemenId() {
        return departemenId;
    }

    public void setDepartemenId(int departemenId) {
        this.departemenId = departemenId;
    }

    
}
