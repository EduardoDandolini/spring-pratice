//package com.example.spring.service;
//
//import com.example.spring.dtos.PersonDTO;
//import com.example.spring.models.Person;
//import com.example.spring.repository.PersonRepository;
//import com.example.spring.service.exceptions.UnprocessableException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.web.client.HttpClientErrorException;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class PersonServiceTest {
//
//    @InjectMocks
//    PersonService personService;
//    @Mock
//    PersonRepository personRepository;
//    PersonDTO personEntity;
//    @BeforeEach
//    public void setUp() {
//        personEntity = new PersonDTO("Eduardo", LocalDate.of(2004, 12, 06));
//    }
//    @Nested
//    class saveNewPerson {
//        @Test
//        void TestSaveNewPerson() {
//            when(personRepository.save(any(PersonDTO.class))).thenReturn(personEntity);
//            PersonDTO personDTO = personService.save(personEntity);
//            assertEquals(personEntity, personDTO);
//        }
//
//        @Test
//        void TestSaveNewPersonIsNull() {
//            assertThrows(UnprocessableException.class, () -> personService.save(null));
//        }
//    }
//        @Nested
//        class TestFindAll {
//            @Test
//            void TestFindAllPerson() {
//                List<PersonDTO> personList = Arrays.asList
//                        (new PersonDTO("Eduardo", LocalDate.of(2004, 12, 06)),
//                                new PersonDTO("Laura", LocalDate.of(2006, 03, 31))
//                        );
//
//                when(personRepository.findAll()).thenReturn(personList);
//
//                List<PersonDTO> personListDTO = personService.findAllPerson();
//
//                assertEquals(2 , personListDTO.size());
//                assertEquals(personList, personListDTO.get(0));
//                assertEquals(personList, personListDTO.get(1));
//            }
//       }
//}
