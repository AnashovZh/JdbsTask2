package org.example.peakSoft.dao.impl;

import org.example.peakSoft.config.Util;
import org.example.peakSoft.dao.JobDao;
import org.example.peakSoft.model.Job;
import org.example.peakSoft.model.JobBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//private Long id;
//private String position;//("Mentor","Management","Instructor") ушундай маанилер берилсин
//private String profession;//("Java","JavaScript")
//private String description;//("Backend developer","Fronted developer")
//private int experience;//(1,2,3........) опыт работы
public class JobDaoImpl implements JobDao {
    private final Connection connection = Util.getconnection();

    @Override
    public void createJobTable() {

        String sql = "create table if not exists jobs (id serial primary key,position varchar," +
                "profession varchar,description varchar,experience int)";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Table successfully created ☺ ");
        } catch (SQLException s) {
            System.out.println(s.getMessage());
        }
    }

    @Override
    public void addJob(Job job) {
        String sql = "insert into jobs (position,profession,description,experience)values(?,?,?,?)";
        try (PreparedStatement prs = connection.prepareStatement(sql)) {
            prs.setString(1, job.getPosition());
            prs.setString(2, job.getProfession());
            prs.setString(3, job.getDescription());
            prs.setInt(4, job.getExperience());
            prs.executeUpdate();
            System.out.println(job.getPosition() + " is saved ☺");
        } catch (SQLException s) {
            System.out.println(s.getMessage());
        }
    }

    @Override
    public Job getJobById(Long jobId) {
        Job job = null;
        String sql = "select * from jobs where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, jobId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                job = new JobBuilder().createJob();
                job.setId(resultSet.getLong("id"));
                job.setPosition(resultSet.getString("position"));
                job.setProfession(resultSet.getString("profession"));
                job.setDescription(resultSet.getString("description"));
                job.setExperience(resultSet.getInt("experience"));
            }
            resultSet.close();
            preparedStatement.close();
            System.out.println("Successfully find jobs ☺");
        } catch (Exception s) {
            System.out.println(s.getMessage());
        }
        return job;
    }

    //    this.id =id;
//        this.position =position;
//        this.profession =profession;
//        this.description =description;
//        this.experience =experience;
    @Override
    public List<Job> sortByExperience(String ascOrDesc) {
        List<Job> jobs = new ArrayList<>();
        String sql = " select * from jobs as j order by j.experience  " + ascOrDesc;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // preparedStatement.setString(1, ascOrDesc);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                jobs.add(new JobBuilder().setId(resultSet.getLong("id")).setPosition(resultSet.getString("position")).setProfession(resultSet.getString("profession")).setDescription(resultSet.getString("description")).setExperience(resultSet.getInt("experience")).createJob());
            }
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException s) {
            System.out.println(s.getMessage());
        }
        return jobs;
    }

    @Override
    public Job getJobByEmployeeId(Long employeeId) {
        Job job = new JobBuilder().createJob();
        String sql = "SELECT j.* from employees e join jobs j on e.job_id=j.id where e.id= ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                job.setId(resultSet.getLong("id"));
                job.setPosition(resultSet.getString("position"));
                job.setProfession(resultSet.getString("profession"));
                job.setDescription(resultSet.getString("description"));
                job.setExperience(resultSet.getInt("experience"));
            }
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException s) {
            System.out.println(s.getMessage());
        }
        return job;
    }

    @Override
    public void deleteDescriptionColumn() {
        String sql = "alter table devices drop column price";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            System.out.println("Successfully deleted column description in jobs");
            ResultSet resultSet = preparedStatement.executeQuery();
        } catch (SQLException s) {
            System.out.println(s.getMessage());
        }
    }

    @Override
    public void addDescriptionColumn(String columnName) {
        String sql = "alter table jobs add column  "+ columnName+" varchar ";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Successfully saved column "+columnName+" to jobs");
            preparedStatement.close();
        }catch (SQLException s){
            System.out.println(s.getMessage());
        }
    }
}
