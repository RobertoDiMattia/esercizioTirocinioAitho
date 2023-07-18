package com.example.demo.api;

import com.example.demo.model.Job;
import com.example.demo.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String addJob(@RequestBody Job job) {
        jobService.addJob(job);
        return "job created successfully";
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
