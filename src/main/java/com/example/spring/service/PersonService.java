package com.example.spring.service;

import com.example.spring.dtos.PersonDTO;
import com.example.spring.models.Person;
import com.example.spring.repository.PersonRepository;
import com.example.spring.service.exceptions.DataBaseException;
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
    public PersonDTO save(PersonDTO personDTO) {
        return personRepository.save(personDTO);
    }
    @Transactional(readOnly = true)
    public List<PersonDTO> findAll() {
        List<PersonDTO> personList = new ArrayList<PersonDTO>();
        personList.stream().forEach(personEntity -> personRepository.findAll());
        return personList;
    }
    @Transactional(readOnly = true)
    public List<PersonDTO>findById(Long id) {
        List<PersonDTO> personList = new ArrayList<>();
        personList.stream().forEach(personEntity -> personRepository.findById(id));
        return personList;
    }
    @Transactional(rollbackFor = Exception.class)
    public PersonDTO update (Long id, PersonDTO personDTO) {
        personDTO = personRepository.getReferenceById(id);
        updateData(personEntity, personDTO);
        return personRepository.save(personDTO);
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

     public void updateData (Person entity, PersonDTO personDTO) {
        entity.setName(personDTO.getName());
        entity.setBirthDate(personDTO.getBirthDate());
     }
    @Transactional(readOnly = true)
    public boolean checksIfPersonExists (Long id) {
        Optional<PersonDTO> personOptional = personRepository.findById(id);
        if (personOptional.isPresent()) {
            return true;
        }
        return false;
    }
}
