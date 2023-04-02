package exercise;

import exercise.entity.Employee;

import java.util.*;

public class EmployeeMain {

    public static void main(String[] args) {
        //create employees
        List<Employee> employeeList = new ArrayList<>();
        Employee.generateEmployees(employeeList);

        //group employees by job title
        Map<String, List<Employee>> groupedEmployees = Employee.groupByJobTitle(employeeList);

        //print grouped employees, grouped by stream
        Employee.printGroupedEmployees(Employee.groupByJobTitleStream(employeeList));

        //print by job title - Software Engineers
        Employee.printEmployeesByTitle(groupedEmployees, "Software Engineer");

        //print average salary for all workers and for Software Engineers
        Employee.printAverageSalary(employeeList);

        System.out.print("Software Engineer ");
        Employee.printAverageSalary(groupedEmployees.get("Software Engineer"));

    }


}
