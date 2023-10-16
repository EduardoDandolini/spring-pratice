package com.example.spring.dtos;

public class AddressDTO {

    private String publicPlace;
    private String zipCode;
    private Integer number;
    private String city;
    private PersonDTO personDTO;

    public AddressDTO() {
    }

    public AddressDTO(String publicPlace, String zipCode, Integer number, String city) {
        this.publicPlace = publicPlace;
        this.zipCode = zipCode;
        this.number = number;
        this.city = city;
    }

    public String getPublicPlace() {
        return publicPlace;
    }

    public void setPublicPlace(String publicPlace) {
        this.publicPlace = publicPlace;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public PersonDTO getPersonDTO() {
        return personDTO;
    }

    public void setPersonDTO(PersonDTO personDTO) {
        this.personDTO = personDTO;
    }
}
