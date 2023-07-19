package com.example.demo.api;

import com.example.demo.model.Job;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/person")
@RestController
@Validated
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/create")
    public ResponseEntity<Person> addPerson(@RequestBody @Valid Person person) {
        return ResponseEntity.ok(personService.addPerson(person));
    }

    @GetMapping
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/jobByPerson")
    public Job getJobByPerson(@RequestParam("name") String name) {
        return personService.retrieveJobByPersonName(name);
    }

    @GetMapping("/jobByNameAndSurnamePerson")
    public Job getJobByNameAndSurname(@RequestParam("name") String name,@RequestParam("surname") String surname) {
        return personService.retrieveJobByPersonNameAndSurname(name,surname);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@RequestBody Person updatedPerson) {
        return ResponseEntity.ok(personService.updatePerson(updatedPerson));
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return "Person deleted successfully";
    }

    @GetMapping("/getNamesByChar")
    public ResponseEntity<String> getNamesByChar(@RequestParam("letter") @NotNull @NotBlank String letter) {
        return ResponseEntity.ok(personService.getNamesByChar(letter));
    }
}
