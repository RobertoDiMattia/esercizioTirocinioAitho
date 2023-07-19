package com.example.demo.api;

import com.example.demo.model.Job;
import com.example.demo.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/job")
@RestController
public class JobController {

    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService){
        this.jobService = jobService;
    }

    @PostMapping("/create")
    public ResponseEntity<Job> addJob(@RequestBody @Valid Job job) {
        return ResponseEntity.ok(jobService.addJob(job));
    }

    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
    }

    @PutMapping("/{id}")
    public Job updateJobName(@PathVariable Long id, @RequestBody Job updatedJob) {
        return jobService.updateJobName(id, updatedJob.getJobName());
    }

}
