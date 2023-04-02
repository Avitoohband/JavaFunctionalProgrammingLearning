package exercise.entity;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public record Customer(Long id, String name, Integer tier) {

    public static Long current_id = 0L;

    @Override
    public String toString() {
        return  "Id:" + id + "\n" +
                "Name:" + name + "\n" +
                "Tier:" + tier + "\n";
    }

    public static void generateCustomers(List<Customer> customerList){


        Stream.generate(() -> new Customer(
                current_id++,
                getRandomName(),
                getRandomTier()
                )).limit(100).forEach(customerList::add);

    }

    private static Integer getRandomTier() {
        return new Random().nextInt(6);
    }

    private static String getRandomName() {

        String[] firstNames = {"John", "Jane", "Mike", "Sara", "David", "Lisa", "Tom", "Emily", "Chris", "Maria"};
        String[] lastNames = {"Smith", "Johnson", "Brown", "Davis", "Garcia", "Wilson", "Clark", "Taylor", "Allen", "Robinson"};
        return firstNames[new Random().nextInt(firstNames.length)] + " " + lastNames[new Random().nextInt(lastNames.length)];

    }
}


