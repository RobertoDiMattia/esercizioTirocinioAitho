package com.example.demo.api;

import com.example.demo.model.Job;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return personService.retrieveJobByPersonNameAndSurname(name);
    }
}
