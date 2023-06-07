package service.Impl;

import entity.Comment;
import entity.Like;
import entity.Person;
import entity.Tweet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

class PersonServiceImplTest {
    PersonServiceImpl personService;

    @InjectMocks
    Person person;
    @InjectMocks
    Tweet tweet;
    @InjectMocks
    Like like;
    @InjectMocks
    Comment comment;


    @BeforeEach
    void setUp() {
        personService = Mockito.mock(PersonServiceImpl.class);
        person = Mockito.mock(Person.class);
        //person=new Person("h","sedghi","1993","un","pass",17 );
    }


    @Test
    void validate() {

       // Mockito.when(personService.validate(person)).thenReturn(true);
        personService.validate(person);
        verify(personService, times(1)).validate(person);
       // Assertions.assertEquals(true, personService.validate(person));
        System.out.println(person.getAge());


    }

    @Test
    void signup() {
        doNothing().when(personService).signUp(isA(Person.class));
        personService.signUp(person);
        verify(personService, times(1)).signUp(person);
    }

}