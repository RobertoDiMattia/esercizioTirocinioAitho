package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "person_id", insertable=false, updatable=false)
    private Long personId;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @Column(name = "job_name")
    private String jobName;

    public Job(){}

    public Job(Long id, Person person, String jobName) {
        this.id = id;
        this.person = person;
        this.jobName = jobName;
    }

    public Long getPersonId() {
        return person != null ? person.getId() : null;
    }

    public void setPersonId(Long personId) {
        if (person == null) {
            person = new Person();
        }
        person.setId(personId);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
}
