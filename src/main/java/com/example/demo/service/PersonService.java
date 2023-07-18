package com.example.demo.service;

import com.example.demo.model.Job;
import com.example.demo.model.Person;
import com.example.demo.repository.JobRepository;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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

    public Job retrieveJobByPersonName(String name) {
        Person person = personRepository.findByName(name);
        return jobRepository.findByPersonId(person.getId());

    }

    public Job retrieveJobByPersonNameAndSurname(String name, String surname) {
        Person person = personRepository.findByNameAndSurname(name,surname);
        return jobRepository.findByPersonId(person.getId());
    }

    public void updatePerson(Long id, Person updatedPerson) {
        Person personToUpdate = personRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Person not found with id: " + id));

        personToUpdate.setName(updatedPerson.getName());
        personToUpdate.setSurname(updatedPerson.getSurname());

        personRepository.save(personToUpdate);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    public List<String> getNamesByChar(String letter) {
        // Esegui la logica di recupero dei nomi dal repository
        return personRepository.findByFirstLetter(letter);
    }

}

