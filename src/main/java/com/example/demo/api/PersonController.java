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

    //Per Senpai (esercizio n. 5), l'ho provato e funziona
    // inizialmente ho provato a farlo con tipo oggetto Character (non mi funzionava)
    @GetMapping("/getNamesByChar")
    public String getNamesByChar(@RequestParam("letter") String letter) {

        if (!isValidInput(letter)) {
            return "Error, input non valido";
        }

        List<String> names = personService.getNamesByChar(letter);

        if (names.isEmpty()) {
            return "Nessuna persona trovata";
        } else {
            return String.join(", ", names);
        }
    }

    private boolean isValidInput(String input) {
        return input.matches("[a-zA-Z]+");
    }

}
