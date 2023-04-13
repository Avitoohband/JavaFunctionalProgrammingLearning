package exercise.entity;

import java.util.*;
import java.util.stream.IntStream;
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
        Map<String, List<String>> categoriesAndProducts = getCategoriesAndProducts();

        IntStream.range(0, 100)
                .mapToObj(i -> getRandomProductName(categoriesAndProducts))
                .forEach(randomProductName -> {
                    String productCategoryName = getProductCategoryName(categoriesAndProducts, randomProductName);
                    productList.add(
                            new Product(
                                    current_id++,
                                    randomProductName,
                                    productCategoryName,
                                    generateRandomPrice()));
                });
    }


    private static Map<String, List<String>> getCategoriesAndProducts() {
        Map<String, List<String>> categoriesAndProducts = new HashMap<>();

        // Add products to the map for "Electronics" category
        addProductToMap(categoriesAndProducts, "Electronics", "DVD", "TV", "Microwave", "Oven",
                "Refrigerator", "Fan", "PC", "Sony", "Stereo", "Monitor");

        // Add products to the map for "Books" category
        addProductToMap(categoriesAndProducts, "Books", "Harry Potter", "Lord of the Rings",
                "To Kill a Mockingbird", "1984", "The Great Gatsby", "Pride and Prejudice",
                "The Catcher in the Rye", "Brave New World");

        addProductToMap(categoriesAndProducts, "Baby", "Diapers", "Baby Formula", "Baby Food",
                "Baby Clothes", "Baby Stroller", "Baby Car Seat", "Baby Monitor", "Baby Toys");

        addProductToMap(categoriesAndProducts, "Toys", "Action Figure", "Doll", "Board Game",
                "Building Blocks", "Remote Control Car", "Puzzle", "Art Set",
                "Plush Toy", "Musical Instrument", "Outdoor Toy");

        return categoriesAndProducts;
    }

    public static Product applyTenPercent(Product p) {
        return p.withPrice(Product.formatPrice((p.price() * 0.9)));
    }

    public static Boolean isBooksCategoryAndOver100(Product product) {
        return "Books".equalsIgnoreCase(product.category()) && (Objects.nonNull(product.price()) && product.price() > 100);
    }

    private static void addProductToMap(Map<String, List<String>> categoriesAndProducts, String category, String... productNames) {
        // If category is not already present in the map, add an empty list for it
        categoriesAndProducts.putIfAbsent(category, new ArrayList<>());

        // Add the product names to the list of products for the given category
        categoriesAndProducts.get(category).addAll(List.of(productNames));
    }

    private static Double generateRandomPrice() {
        double randomPrice = new Random().nextDouble() * 150 + 10;
        return formatPrice(randomPrice);
    }

    public static Double formatPrice(Double price){
        String formattedPriceString = String.format("%.2f", price);
        return Double.valueOf(formattedPriceString);
    }

    private static String getRandomProductName(Map<String, List<String>> categoriesAndProducts) {
        // Get a random category from the product map
        String randomCategory = getRandomCategory(categoriesAndProducts);

        // Get the list of product names for the random category
        List<String> productNames = categoriesAndProducts.get(randomCategory);

        // Get a random product name from the list
        return productNames.get(new Random().nextInt(productNames.size()));
    }

    private static String getRandomCategory(Map<String, List<String>> categoriesAndProducts) {
        // Get a random category key from the product map
        List<String> categories = new ArrayList<>(categoriesAndProducts.keySet());
        return categories.get(new Random().nextInt(categories.size()));
    }

    private static String getProductCategoryName(Map<String, List<String>> categoriesAndProducts, String randomProductName) {
        return categoriesAndProducts
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().contains(randomProductName))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse("General");

    }

    public Product withPrice(double newPrice) {
        return new Product(id, name, category, newPrice);
    }
}

