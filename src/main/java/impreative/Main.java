package impreative;

import java.util.ArrayList;
import java.util.List;

import static impreative.Main.Person.Gender.MALE;
import static impreative.Main.Person.Gender.FEMALE;

public class Main {

    public static void main(String[] args) {
        List<Person> people = List.of(
                new Person("John", MALE),
                new Person("Maria", FEMALE),
                new Person("Alisha", FEMALE),
                new Person("Alex", MALE),
                new Person("Alice", FEMALE)
        );

        List<Person> females = new ArrayList<>();
        System.out.println("// Imperative approach");
        // Imperative approach
        for (Person p : people) {
            if (p.gender.equals(FEMALE)) {
                females.add(p);
            }
        }
        for (Person f : females) {
            System.out.println(f);
        }

        System.out.println("// Declarative approach");
        // Declarative approach
        people.stream() // make a stream out of people
                .filter(p -> p.gender.equals(FEMALE)) // filter each person by a gender compute
                .forEach(System.out::println); // method reference to print each person
        // could be lambda as well: f -> System.out.println(f)
    }

    static class Person {
        private final String name;
        private final Gender gender;


        Person(String name, Gender gender) {
            this.name = name;
            this.gender = gender;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", gender=" + gender +
                    '}';
        }

        enum Gender {
            MALE, FEMALE
        }
    }
}

