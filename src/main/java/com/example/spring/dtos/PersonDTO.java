package com.example.spring.dtos;

import java.time.LocalDate;

public class PersonDTO {

    private Long id;
    private String name;
    private LocalDate birthDate;

    public PersonDTO() {

    }

    public PersonDTO(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public PersonDTO(Long id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
