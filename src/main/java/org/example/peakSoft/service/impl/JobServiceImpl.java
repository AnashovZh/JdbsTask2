package org.example.peakSoft.service.impl;

import org.example.peakSoft.dao.JobDao;
import org.example.peakSoft.dao.impl.JobDaoImpl;
import org.example.peakSoft.model.Job;
import org.example.peakSoft.service.JobService;

import java.util.List;

public class JobServiceImpl implements JobService {
    JobDao jobDao=new JobDaoImpl();
    @Override
    public void createJobTable() {
        jobDao.createJobTable();

    }

    @Override
    public void addJob(Job job) {
        jobDao.addJob(job);

    }

    @Override
    public Job getJobById(Long jobId) {
        return jobDao.getJobById(jobId);
    }

    @Override
    public List<Job> sortByExperience(String ascOrDesc) {
        return jobDao.sortByExperience(ascOrDesc);
    }

    @Override
    public Job getJobByEmployeeId(Long employeeId) {
        return jobDao.getJobByEmployeeId(employeeId);
    }

    @Override
    public void deleteDescriptionColumn() {
        jobDao.deleteDescriptionColumn();

    }

    @Override
    public void addDescriptionColumn(String columnName) {
        jobDao.addDescriptionColumn(columnName);
    }
}
