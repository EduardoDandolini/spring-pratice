package com.example.spring.controllers;

import com.example.spring.dtos.PersonDTO;
import com.example.spring.models.Person;
import com.example.spring.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    PersonService personService;
    @PostMapping(value = "/save")
    public ResponseEntity<PersonDTO> save(@RequestBody PersonDTO personDTO)  {
        personDTO = personService.save(personDTO);
        return ResponseEntity.ok().body(personDTO);
    }
    @GetMapping(value = "/findById/{id}")
    public ResponseEntity findById(@PathVariable(required = true) Long id) {
        List<PersonDTO> personDTO = personService.findById(id);
        return ResponseEntity.ok().body(personDTO);
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity findAll() {
        List<PersonDTO> personList = new ArrayList<>();
        personList = personService.findAll();
        return ResponseEntity.ok().body(personList);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<PersonDTO> update(@PathVariable  Long id, @RequestBody PersonDTO personDTO) {
        personDTO = personService.update(id, personDTO);
        return ResponseEntity.ok().body(personDTO);
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
