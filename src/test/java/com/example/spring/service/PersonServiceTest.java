package com.example.spring.service;

import com.example.spring.models.Person;
import com.example.spring.repository.PersonRepository;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @InjectMocks
    PersonService personService;
    @Mock
    PersonRepository personRepository;
    Person person;
    String name;
    LocalDate birthDate;
    @BeforeEach
    public void setUp() {
        person = new Person("Eduardo","06/12/2004" );
    }
    @Test
    void personFindByIdTeste() {
        when(personService.findById(person.getId())).thenReturn(Collections.singletonList(person));

        List<Person> personList = personService.findById(person.getId());

        assertEquals(Collections.singletonList(person), personList);
        verify(personService).findById(person.getId());
        verifyNoMoreInteractions(personService);
    }
}
