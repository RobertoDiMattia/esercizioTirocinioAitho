package com.example.demo.service;

import com.example.demo.model.Job;
import com.example.demo.model.Person;
import com.example.demo.repository.JobRepository;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private JobRepository jobRepository;

    public String addPerson(Person person) {
        personRepository.save(person);
        return "added person";
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person retrievePersonByName(String name) {
        return personRepository.findByName(name);
    }

    public Job retrieveJobByPersonNameAndSurname(String name) {
        Person person = personRepository.findByName(name);
        return jobRepository.findByPersonId(person.getId());

    }
}

