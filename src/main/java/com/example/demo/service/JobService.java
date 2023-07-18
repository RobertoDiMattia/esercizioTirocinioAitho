package com.example.demo.service;

import com.example.demo.model.Job;
import com.example.demo.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    public Job updateJobName(Long id, String newJobName) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Job not found with id: " + id));

        job.setJobName(newJobName);

        return jobRepository.save(job);
    }
}
