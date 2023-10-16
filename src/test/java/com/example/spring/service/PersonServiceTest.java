package com.example.spring.service;

import com.example.spring.dtos.PersonDTO;
import com.example.spring.models.Person;
import com.example.spring.repository.PersonRepository;
import com.example.spring.service.exceptions.UnprocessableException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @InjectMocks
    PersonService personService;
    @Mock
    PersonRepository personRepository;
    Person personEntity;
    @BeforeEach
    public void setUp() {
        personEntity = new Person("Eduardo", LocalDate.of(2004, 12, 06));
    }
    @Nested
    class saveNewPerson {
        @Test
        void TestSaveNewPerson() {
            when(personRepository.save(any(Person.class))).thenReturn(personEntity);
            Person personDTO = personService.save(personEntity);
            assertEquals(personEntity, personDTO);

            verify(personRepository, times(1));
        }

        @Test
        void TestSaveNewPersonIsNull() {
            assertThrows(UnprocessableException.class, () -> personService.save(null));
        }
    }
        @Nested
        class TestFindAll {
            @Test
            void TestFindAllPerson() {
                List<Person> personList = Arrays.asList
                        (new Person("Eduardo", LocalDate.of(2004, 12, 06)),
                                new Person("Laura", LocalDate.of(2006, 03, 31))
                        );

                when(personRepository.findAll()).thenReturn(personList);

                List<PersonDTO> personListDTO = personService.findAllPerson();

                assertEquals(2 , personListDTO.size());
                assertEquals(personList.get(0).getName(), personListDTO.get(0).getName());
                assertEquals(personList.get(0).getBirthDate(), personListDTO.get(0).getBirthDate());
                assertEquals(personList.get(1).getName(), personListDTO.get(1).getName());
                assertEquals(personList.get(1).getBirthDate(), personListDTO.get(1).getBirthDate());
            }
       }

       @Nested
       class TestFindById {
            @Test
            void TestFindPersonById() {
                Person person = new Person(1L,"Eduardo", LocalDate.of(2004, 12, 06));

                when(personRepository.findById(any(Long.class))).thenReturn(Optional.of(person));
                Person personEntity = personService.findById(1L);

                assertEquals(person.getName(), personEntity.getName());
                assertEquals(person.getBirthDate(), personEntity.getBirthDate());
                assertEquals(1L, personEntity.getId());
            }
       }

       @Nested
       class TestUpdatePerson {
            @Test
            void TestUpdatePersonById() {
                PersonDTO personDTO = new PersonDTO(1L, "Eduardo", LocalDate.of(2004, 12, 06));
                Person person = new Person(1L, "EduardoTeste", LocalDate.of(2004, 12, 06));

                when(personRepository.findById(any(Long.class))).thenReturn(Optional.of(person));
                when(personRepository.save(any(Person.class))).thenReturn(person);
                Person personUpdate = personService.update(1L, personDTO);

            }
       }
}
