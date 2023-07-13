package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public String addPerson(Person person) {
        return personRepository.insertPerson(person);
    }

    public List<Person> getAllPersons() {
        return personRepository.selectAllPersons();
    }

}

