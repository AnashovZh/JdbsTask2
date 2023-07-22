package org.example.peakSoft.dao;

import org.example.peakSoft.model.Job;

import java.util.List;

public interface JobDao {
    void createJobTable();
    void addJob(Job job);
    Job getJobById(Long jobId);
    List<Job> sortByExperience(String ascOrDesc);
    Job getJobByEmployeeId(Long employeeId);
    void deleteDescriptionColumn();
    void addDescriptionColumn(String columnName);
}
