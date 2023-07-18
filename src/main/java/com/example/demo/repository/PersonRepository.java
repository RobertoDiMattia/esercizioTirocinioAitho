package com.example.demo.repository;

import com.example.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByName(String name);

    Person findByNameAndSurname(String name, String surname);

    @Query("SELECT p.name FROM Person p WHERE SUBSTRING(p.name, 1, 1) = :letter")
    List<String> findByFirstLetter(@Param("letter") String letter);

}
