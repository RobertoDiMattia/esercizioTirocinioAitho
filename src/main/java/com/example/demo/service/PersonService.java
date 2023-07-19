package com.example.demo.service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Job;
import com.example.demo.model.Person;
import com.example.demo.repository.JobRepository;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private JobRepository jobRepository;

    public Person addPerson(Person person) {
        return personRepository.save(person);
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

    public Person updatePerson(Person updatedPerson) {
            Person person = personRepository.findById(updatedPerson.getId())
                    .orElseThrow(() -> new NotFoundException("Person not found with id: " + updatedPerson.getId()));

            person.setName(updatedPerson.getName());
            person.setSurname(updatedPerson.getSurname());

            return personRepository.save(person);
    }

    public ResponseEntity<?> deletePerson(Long id) {
        personRepository.deleteById(id);
        return ResponseEntity.ok("The person with id : " + id + " is deleted");
    }

    public String getNamesByChar(String letter) {

        List<String> names = personRepository.findByFirstLetter(letter);
        if (names.isEmpty()) {
            throw new NotFoundException("No records found");
        }
        return String.join(", ", names);
    }
          // metodo precedente usando lo STREAM
//        return names.stream().collect(Collectors.joining(", "));

          // metodo precedente usando STREAM OF
//        return stream.of(names).collect(Collectors.joining(", "));

}

