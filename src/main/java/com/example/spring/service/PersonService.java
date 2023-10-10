package com.example.spring.service;

import com.example.spring.dtos.PersonDTO;
import com.example.spring.models.Person;
import com.example.spring.repository.PersonRepository;
import com.example.spring.service.exceptions.DataBaseException;
import com.example.spring.service.exceptions.NotFoundException;
import com.example.spring.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    private Person personEntity = new Person();

    private PersonDTO personDTO = new PersonDTO();

    @Transactional(rollbackFor = Exception.class)
    public Person save(Person person) {
        return personRepository.save(person);
    }
    @Transactional(readOnly = true)
    public List<Person> findAll() {
        List<Person> personList = new ArrayList<Person>();
        personList.stream().forEach(personEntity -> personRepository.findAll());
        return personList;
    }

    @Transactional(readOnly = true)
    public List<Person> findAllPerson() {
        return personRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Person findById(Long id) {
        Optional<Person> personList = personRepository.findById(id);
        if (!personList.isPresent()) {
            throw new NotFoundException("Person not found");
        }
        return personList.get();
    }
    @Transactional(rollbackFor = Exception.class)
    public Person update (Long id, Person person) {
        person = personRepository.getReferenceById(id);
        updateData(personEntity, person);
        return personRepository.save(person);
    }
    @Transactional(rollbackFor = Exception.class)
    public void delete (Long id) {
        try {
            personRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

     public void updateData (Person entity, Person person) {
        entity.setName(person.getName());
        entity.setBirthDate(person.getBirthDate());
     }
    @Transactional(readOnly = true)
    public boolean checksIfPersonExists (Long id) {
        Optional<Person> personOptional = personRepository.findById(id);
        if (personOptional.isPresent()) {
            return true;
        }
        return false;
    }
}
