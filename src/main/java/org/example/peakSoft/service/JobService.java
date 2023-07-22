package org.example.peakSoft.service;

import org.example.peakSoft.model.Job;

import java.util.List;

public interface JobService {
    void createJobTable();
    void addJob(Job job);
    Job getJobById(Long jobId);
    List<Job> sortByExperience(String ascOrDesc);
    Job getJobByEmployeeId(Long employeeId);
    void deleteDescriptionColumn();
    void addDescriptionColumn(String columnName);

}
