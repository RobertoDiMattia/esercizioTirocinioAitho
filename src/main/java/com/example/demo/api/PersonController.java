package com.example.demo.api;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Job;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> addPerson(@RequestBody @Valid Person person) {
        personService.addPerson(person);
        return ResponseEntity.ok("person created successfully");
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
    public String updatePerson(@PathVariable Long id, @RequestBody Person updatedPerson) {
        personService.updatePerson(id, updatedPerson);
        return "Person updated successfully";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return "Person deleted successfully";
    }

    @GetMapping("/getNamesByChar")
    public ResponseEntity<String> getNamesByChar(@RequestParam("letter") String letter) {
        try {
            String namesByChar = personService.getNamesByChar(letter);
            return ResponseEntity.status(HttpStatus.OK).body(namesByChar);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Forced error: " + e.getMessage());
        }
    }
}
