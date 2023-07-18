package com.example.demo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Person {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Job> jobs = new ArrayList<>();

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    public Person(){}

    public Person(Long id, List<Job> jobs, String name, String surname) {
        this.id = id;
        this.jobs = jobs;
        this.name = name;
        this.surname = surname;
    }

    public void addJob(Job job) {
        if (jobs == null) {
            jobs = new ArrayList<>();
        }
        jobs.add(job);
        job.setPerson(this);
    }

    public void removeJob(Job job) {
        if (jobs != null) {
            jobs.remove(job);
            job.setPerson(null);
        }
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
