package org.example;

import org.example.peakSoft.model.Employee;
import org.example.peakSoft.model.Job;
import org.example.peakSoft.service.EmployeeService;
import org.example.peakSoft.service.JobService;
import org.example.peakSoft.service.impl.EmployeeServiceIml;
import org.example.peakSoft.service.impl.JobServiceImpl;

import javax.sound.midi.SysexMessage;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        JobService jobService =new JobServiceImpl();
        EmployeeService employeeService=new EmployeeServiceIml();
        while(true){
            System.out.println(" 1   createJobTable\n" +
                    " 2   addJob\n" +
                    " 3   getJobById\n" +
                    " 4   sortByExperience\n" +
                    " 5   getJobByEmployeeId\n" +
                    " 6   deleteDescriptionColumn\n" +
                    " 7   addDescriptionColumn");
            System.out.println(
                    "  8   createEmployee\n" +
                    "  9   addEmployee\n" +
                    "  10  dropTable\n" +
                    "  11  cleanTable\n" +
                    "  12  updateEmployee\n" +
                    "  13  getAllEmployees\n" +
                    "  14  findByEmail\n" +
                    "  15  getEmployeeById\n" +
                    "  16  getEmployeeByPosition");
            int n=new Scanner(System.in).nextInt();
            switch (n){
                case 1->{
                    System.out.println("createJobTable");
                    jobService.createJobTable();
                }
                case 2->{
                    System.out.println("addJob");
                    System.out.println("write position");
                    String position=new Scanner(System.in).nextLine();
                    System.out.println("Write profession");
                    String profession=new Scanner(System.in).nextLine();
                    System.out.println("write description");
                    String description=new Scanner(System.in).nextLine();
                    System.out.println("Write experience");
                    int experience=new Scanner(System.in).nextInt();
                    jobService.addJob(new Job(position,profession,description,experience));
                }
                case 3->{
                    System.out.println("get job by id");
                    System.out.println("write job id");
                    Long id=new Scanner(System.in).nextLong();
                    System.out.println(jobService.getJobById(id));
                }
                case 4->{
                    System.out.println("sort by experience");
                    System.out.println("Write asc or desc");
                    String ascOrDesc=new Scanner(System.in).nextLine();
                    System.out.println(jobService.sortByExperience(ascOrDesc));
                }
                case 5->{
                    System.out.println("Get job by employee id");
                    System.out.println("Write employee id");
                    Long employeeid=new Scanner(System.in).nextLong();
                    System.out.println(jobService.getJobByEmployeeId(employeeid));
                }
                case 6->{
                    System.out.println("Delete description column");
                    jobService.deleteDescriptionColumn();
                }
                case 7->{
                    System.out.println("Add description column to table jobs");
                    System.out.println("write column name:");
                    String columName=new Scanner(System.in).nextLine();
                    jobService.addDescriptionColumn(columName);
                }
                case 8->{
                    System.out.println("Create table employee");
                    employeeService.createEmployee();
                }
                case 9->{
                    System.out.println("Add employee");
                    System.out.println("Write first_name");
                    String firstName=new Scanner(System.in).nextLine();
                    System.out.println("Write last_name");
                    String lastName=new Scanner(System.in).nextLine();
                    System.out.println("Write age");
                    int age=new Scanner(System.in).nextInt();
                    System.out.println("write email");
                    String email=new Scanner (System.in).nextLine();
                    System.out.println("write job_id");
                    int job_id =new Scanner (System.in).nextInt();
                    employeeService.addEmployee(new Employee(firstName,lastName,age,email,job_id));
                }
                case 10 ->{
                    System.out.println("Drop table ");
                    employeeService.dropTable();
                }
                case 11->{
                    System.out.println("Clean table");
                    employeeService.cleanTable();
                }
                case 12->{
                    System.out.println("Update employee");
                    System.out.println("Write employee id");
                    Long id=new Scanner(System.in).nextLong();
                    System.out.println("Write firstNname");
                    String firstName=new Scanner(System.in).nextLine();
                    System.out.println("Write lastNname");
                    String lastName=new Scanner(System.in).nextLine();
                    System.out.println("Write age");
                    int age=new Scanner(System.in).nextInt();
                    System.out.println("write email");
                    String email=new Scanner (System.in).nextLine();
                    System.out.println("write job_id");
                    int job_id =new Scanner (System.in).nextInt();
                    employeeService.updateEmployee(id,new Employee(firstName,lastName,age,email,job_id));
                }
                case 13->{
                    System.out.println("get all employees");
                    employeeService.getAllEmployees().forEach(System.out::println);
                }
                case 14->{
                    System.out.println("find employee by email");
                    System.out.println("Write employee email");
                    String email=new Scanner(System.in).nextLine();
                    System.out.println(employeeService.findByEmail(email));
                }
                case 15->{
                    System.out.println("Get employee by id (MAP)");
                    System.out.println("Write employee id ");
                    Long id =new Scanner(System.in).nextLong();
                    System.out.println(employeeService.getEmployeeById(id));
                }
                case 16->{
                    System.out.println("Get employee by position ");
                    System.out.println("Write employee position");
                    String position =new Scanner(System.in).nextLine();
                    System.out.println(employeeService.getEmployeeByPosition(position));
                }
            }
        }
    }
}
