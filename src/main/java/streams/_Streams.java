package streams;

import java.util.List;
import java.util.stream.Collectors;

import static streams._Streams.Person.Gender.FEMALE;
import static streams._Streams.Person.Gender.MALE;

public class _Streams {
    public static void main(String[] args) {

        List<Person> people = List.of(
                new Person("John", MALE),
                new Person("Maria", FEMALE),
                new Person("Alisha", FEMALE),
                new Person("Alex", MALE),
                new Person("Alice", FEMALE)
        );

        System.out.println("// Using stream to map people List into Genders");
        people
                .stream() // getting data into the stream
                .map(p -> p.gender) // takes people and map into gender  - Function
                .collect(Collectors.toSet()) // collect into set to remove duplicates
                .forEach(System.out::println); // print with Consumer function each gender

        System.out.println("// Using stream to map people List into String length");
        people
                .stream() // get the data into a stream
                .map(_Streams::nameToLength) // Function that takes People object and produce string length
                .forEach(System.out::println); // Consumer that takes string length and print it

        System.out.println("// Using stream to check if we at least one Females");
        System.out.println(
                people.stream()
                .anyMatch(person -> person.gender.equals(FEMALE)));
    }

    private static Integer nameToLength(Person p) {
        return p.name.length();
    }

    static class Person {
        private final String name;
        private final Person.Gender gender;


        Person(String name, Person.Gender gender) {
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



