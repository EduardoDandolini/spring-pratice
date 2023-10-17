package com.example.spring.controllers;

import com.example.spring.dtos.PersonDTO;
import com.example.spring.models.Person;
import com.example.spring.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    PersonService personService;
    @PostMapping(value = "/save")
    public ResponseEntity<Person> save(@RequestBody Person person)  {
        person = personService.save(person);
        return ResponseEntity.ok().body(person);
    }
    @GetMapping(value = "/findById/{id}")
    public ResponseEntity findById(@PathVariable(required = true) Long id) {
        Person person = personService.findById(id);
        return ResponseEntity.ok().body(person);
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity findAll() {
        List<Person> personList = new ArrayList<>();
        personList = personService.findAll();
        return ResponseEntity.ok().body(personList);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<PersonDTO> update(@RequestBody PersonDTO person) {
        PersonDTO personDTO = personService.update(person);
        return ResponseEntity.ok().body(person);
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
