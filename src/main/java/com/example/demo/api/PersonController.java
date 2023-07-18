package com.example.demo.api;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Job;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/create")
    public String addPerson(@RequestBody Person person) {
        personService.addPerson(person);
        return "Person created successfully";
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
    public Job getJobByNaneAndSurname(@RequestParam("name") String name,@RequestParam("surname") String surname) {
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
        if (!isValidInput(letter)) {
            throw new BadRequestException("Error 400");
        }

        List<String> names = personService.getNamesByChar(letter);

        if (names.isEmpty()) {
            throw new NotFoundException("Error 404");
        } else {
            String response = String.join(", ", names);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    private boolean isValidInput(String input) {
        return input.matches("[a-zA-Z]+");
    }
}
