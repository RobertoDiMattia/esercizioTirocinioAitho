package com.example.demo.repository;

import com.example.demo.model.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class PersonRepository {

    public static final String THE_PERSON_HAS_BEEN_ADDED = "The person has been added";
    List<Person> personList = new ArrayList<>();

    public String insertPerson(Person person) {
        Person persona = new Person();
        persona.setName(person.getName());
        persona.setId(UUID.randomUUID());
        personList.add(persona);
        return THE_PERSON_HAS_BEEN_ADDED;
    }

    public List<Person> selectAllPersons() {
        return personList;
    }
}
