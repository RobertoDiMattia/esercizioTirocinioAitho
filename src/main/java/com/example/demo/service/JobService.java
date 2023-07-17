package com.example.demo.service;

import com.example.demo.model.Job;
import com.example.demo.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public String addJob(Job job) {
        jobRepository.save(job);
        return "added job";
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }
}
