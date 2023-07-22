package org.example.peakSoft.service.impl;

import org.example.peakSoft.dao.EmployeeDao;
import org.example.peakSoft.dao.impl.EmployeeDaoImpl;
import org.example.peakSoft.model.Employee;
import org.example.peakSoft.model.Job;
import org.example.peakSoft.service.EmployeeService;

import java.util.List;
import java.util.Map;

public class EmployeeServiceIml implements EmployeeService{
    EmployeeDao employeeDao=new EmployeeDaoImpl();

    @Override
    public void createEmployee() {
        employeeDao.createEmployee();
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeDao.addEmployee(employee);

    }

    @Override
    public void dropTable() {
        employeeDao.dropTable();

    }

    @Override
    public void cleanTable() {
        employeeDao.cleanTable();
    }

    @Override
    public void updateEmployee(Long id, Employee employee) {
        employeeDao.updateEmployee(id,employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    @Override
    public Employee findByEmail(String email) {
        return employeeDao.findByEmail(email);
    }

    @Override
    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        return employeeDao.getEmployeeById(employeeId);
    }

    @Override
    public List<Employee> getEmployeeByPosition(String position) {
        return employeeDao.getEmployeeByPosition(position);
    }
}
