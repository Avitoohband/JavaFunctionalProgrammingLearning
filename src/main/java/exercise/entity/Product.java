package exercise.entity;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public record Product(Long id, String name, String category, Double price) {

    public static Long current_id = 0L;


    @Override
    public String toString() {
        return "Id:" + id + "\n" +
                "Name:" + name + "\n" +
                "Category:" + category + "\n" +
                "Price:" + String.format("%,.2f", price) + "\n";
    }

    public static void generateProducts(List<Product> productList) {
        Stream.generate(() -> new Product(
                current_id++,
                getRandomProductName(),
                getRandomCategory(),
                generateRandomPrice())
        ).limit(100).forEach(productList::add);
    }

    private static Double generateRandomPrice() {
        return new Random().nextDouble() * 50_000 + 1_000;
    }

    private static String getRandomCategory() {
        String[] categoryNames = {"Electricity", "Super Electricity", "Low Electricity", "Fulled", "Space Ship"};
        return categoryNames[new Random().nextInt(categoryNames.length)];
    }

    private static String getRandomProductName() {

        String[] productNames = {"DVD", "TV", "Microwave", "Oven", "Refrigerator", "Fan", "PC", "Sony", "Stereo", "Monitor"};
        return productNames[new Random().nextInt(productNames.length)];


    }
}
