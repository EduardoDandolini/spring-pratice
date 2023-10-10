package com.example.spring.models;

import com.example.spring.dtos.AddressDTO;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "tb_address")
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String publicPlace;
    @Column
    private String zipCode;
    @Column
    private Integer number;
    @Column
    private String city;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_person", nullable = false)
    private Person person;

    public Address() {

    }

    public Address(AddressDTO addressDTO) {
        this.publicPlace = addressDTO.getPublicPlace();
        this.zipCode = addressDTO.getZipCode();
        this.number = addressDTO.getNumber();
        this.city = addressDTO.getCity();
    }

    public Address(String publicPlace, String zipCode, Integer number, String city, boolean main, Person person) {
        this.publicPlace = publicPlace;
        this.zipCode = zipCode;
        this.number = number;
        this.city = city;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public AddressDTO addresToDto() {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setPublicPlace(publicPlace);
        addressDTO.setZipCode(zipCode);
        addressDTO.setCity(city);
        addressDTO.setNumber(number);
        addressDTO.setPersonDTO(person.personToDto());
        return addressDTO;
    }
}
