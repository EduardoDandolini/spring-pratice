package com.example.spring.dtos;

import java.time.LocalDate;

public class PersonDTO {

    private String name;
    private LocalDate birthDate;

    public PersonDTO() {

    }

    public PersonDTO(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
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
