package exercise.entity;

import java.util.*;
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



    public void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println(String.format("Salary:  $%,.2f", salary));
        System.out.println("Job Title: " + jobTitle);
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
        employeeList.forEach(employee -> {
            employee.displayDetails();
            System.out.println();
        });
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

    public static void printSoftwareEngineers(Map<String, List<Employee>> groupedEmployees) {
        System.out.println("Software Engineers:");
        groupedEmployees.entrySet().stream()
                .filter(e -> e.getKey().equals("Software Engineer"))
                .forEach(e -> Employee.printEmployees(e.getValue()));
    }

    public static void printGroupedEmployees(Map<String, List<Employee>> groupedEmployees) {
        groupedEmployees.entrySet().stream()
                .sorted(Comparator.comparing(e -> e.getValue().size()))
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue().size()));
    }
}
