package org.example.peakSoft.dao.impl;

import org.example.peakSoft.config.Util;
import org.example.peakSoft.model.Job;
import org.example.peakSoft.dao.EmployeeDao;
import org.example.peakSoft.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDaoImpl implements EmployeeDao {
    private final Connection connection = Util.getconnection();

    //     this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.age = age;
//        this.email = email;
//        this.jobId = jobId;
    @Override
    public void createEmployee() {
        String sql = "create table if not exists employees (id serial primary key,first_name varchar," +
                "last_name varchar,age int ,email varchar,job_id int references jobs(id));";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Successfully created table");
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    @Override
    public void addEmployee(Employee employee) {
        String sql = "insert into employees (first_name,last_name,age,email,job_id)values (?,?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setInt(3, employee.getAge());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setLong(5, employee.getJobId());
            preparedStatement.executeUpdate();
            System.out.println("Successfully saved employee ☺");
        } catch (SQLException s) {
            System.out.println(s.getMessage());
        }
    }

    @Override
    public void dropTable() {
        String sql = " drop table tea cascade ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Successfully deleted table");
        } catch (SQLException s) {
            System.out.println(s.getMessage());
        }

    }

    @Override
    public void cleanTable() {
        String sql = "delete table users";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Successfully deleted table clean");
        } catch (SQLException s) {
            System.out.println(s.getMessage());
        }
    }

    @Override
    public void updateEmployee(Long id, Employee employee) {
        String sql = "update employees set first_name=?,last_name=?,age=?,email=?,job_id=? where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setInt(3, employee.getAge());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setLong(5, employee.getJobId());
            preparedStatement.setLong(6, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Successfully update ☺" + employee.getFirstName());
        } catch (SQLException s) {
            System.out.println(s.getMessage());
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "select * from employees ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employees.add(new Employee
                        (resultSet.getLong("id"),
                                resultSet.getString("first_name"),
                                resultSet.getString("last_name"),
                                resultSet.getInt("age"),
                                resultSet.getString("email"),
                                resultSet.getInt("job_id")));
            }
        } catch (SQLException s) {
            System.out.println(s.getMessage());
        }
        return employees;
    }

    @Override
    public Employee findByEmail(String email) {
        Employee employee=new Employee();
        String sql="select * from employees where email=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                employee.setId(resultSet.getLong("id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setAge(resultSet.getInt("age"));
                employee.setEmail(resultSet.getString("email"));
                employee.setJobId(resultSet.getInt("job_id"));
            }else {
                System.out.println("not found email");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }

    @Override
    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        Map<Employee,Job>map=new HashMap<>();
        String sql="select * from employees e join jobs j on e.job_id=j.id where e.id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                map.put(new Employee(resultSet.getLong("id"),resultSet.getString("first_name"),
               resultSet.getString("last_name"),resultSet.getInt("age"),
               resultSet.getString("email"),resultSet.getInt("job_id")),new Job(
               resultSet.getLong("id"), resultSet.getString("position"),
               resultSet.getString("profession"),resultSet.getString("description"),
               resultSet.getString("experience")));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    @Override
    public List<Employee> getEmployeeByPosition(String position) {
        List<Employee>employees=new ArrayList<>();
        String sql="select * from employees e join jobs j on e.job_id=j.id where j.position=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,position);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                employees.add(new Employee
                        (resultSet.getLong("id"),
                                resultSet.getString("first_name"),
                                resultSet.getString("last_name"),
                                resultSet.getInt("age"),
                                resultSet.getString("email"),
                                resultSet.getInt("job_id")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }
}
