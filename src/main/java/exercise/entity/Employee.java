package exercise.entity;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record Employee(String name, int age, double salary, String jobTitle) {
    public Employee {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }
    }

    public Employee withSalary(double salary) {
        return new Employee(name, age, salary, jobTitle);
    }

    public Employee withJobTitle(String jobTitle) {
        return new Employee(name, age, salary, jobTitle);
    }

    public Employee withAge(int age) {
        return new Employee(name, age, salary, jobTitle);
    }

    public Employee withName(String name) {
        return new Employee(name, age, salary, jobTitle);
    }



    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Age: " + age + "\n" +
                "Salary: " + String.format("%,.2f", salary) + "\n" +
                "Job Title: " + jobTitle + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;

        if (age != employee.age) return false;
        if (Double.compare(employee.salary, salary) != 0) return false;
        if (!name.equals(employee.name)) return false;
        return jobTitle.equals(employee.jobTitle);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        result = 31 * result + age;
        temp = Double.doubleToLongBits(salary);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + jobTitle.hashCode();
        return result;
    }

    public static void generateEmployees(List<Employee> employeeList) {
        Random random = new Random();

        Stream.generate(() -> new Employee(
                        generateRandomName(),
                        random.nextInt(50) + 20,
                        random.nextDouble() * 100000,
                        generateRandomJobTitle()))
                .limit(100)
                .forEach(employeeList::add);
    }

    public static void printEmployees(List<Employee> employeeList) {
        employeeList.forEach(System.out::println);
    }


    // helper methods to generate random values
    private static String generateRandomName() {
        String[] firstNames = {"John", "Jane", "Mike", "Sara", "David", "Lisa", "Tom", "Emily", "Chris", "Maria"};
        String[] lastNames = {"Smith", "Johnson", "Brown", "Davis", "Garcia", "Wilson", "Clark", "Taylor", "Allen", "Robinson"};
        return firstNames[new Random().nextInt(firstNames.length)] + " " + lastNames[new Random().nextInt(lastNames.length)];
    }

    private static String generateRandomJobTitle() {
        String[] jobTitles = { "Software Engineer", "Data Scientist", "Product Manager", "Marketing Specialist", "Sales Representative" };
        return jobTitles[new Random().nextInt(jobTitles.length)];
    }

    public static Map<String, List<Employee>> groupByJobTitle(List<Employee> employeeList) {
        Map<String, List<Employee>> resultMap = new HashMap<>();
        for (Employee employee : employeeList) {
            List<Employee> employeeSubList = resultMap.getOrDefault(employee.jobTitle(), new ArrayList<Employee>());
            employeeSubList.add(employee);
            resultMap.put(employee.jobTitle(), employeeSubList);
        }
        return resultMap;
    }

    public static Map<String, List<Employee>> groupByJobTitleStream(List<Employee> employeeList){
        return employeeList.stream()
                .collect(Collectors.groupingBy(Employee::jobTitle));
    }

    public static double calculateAverage (List<Employee> employeeList){
        return employeeList.stream()
                .mapToDouble(Employee::salary)
                .average()
                .orElse(0.0);
    }

    public static void printAverageSalary (List<Employee> employeeList){
        System.out.printf("Average Salary: $%,.2f%n",calculateAverage(employeeList));
    }

    public static void printEmployeesByTitle(Map<String, List<Employee>> groupedEmployees, String title) {
        System.out.println(title + ":");
        groupedEmployees.entrySet().stream()
                .filter(e -> e.getKey().equals(title))
                .forEach(e -> Employee.printEmployees(e.getValue()));
    }

    public static void printGroupedEmployees(Map<String, List<Employee>> groupedEmployees) {
        groupedEmployees.entrySet().stream()
                .sorted(Comparator.comparing(e -> e.getValue().size()))
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue().size()));
    }
}
