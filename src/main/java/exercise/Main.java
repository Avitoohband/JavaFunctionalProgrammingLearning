package exercise;

import exercise.entity.Employee;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<Employee> employeeList = new ArrayList<>();
        Employee.generateEmployees(employeeList);
        Map<String, List<Employee>> groupedEmployees = Employee.groupByJobTitle(employeeList);



    }


}
