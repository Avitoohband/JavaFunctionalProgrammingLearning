package exercise.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public record Order(Long id, String status, LocalDate orderDate, LocalDate deliveryDate, List<Product> productList,
                    Customer customer) {

    public static Long current_id = 0L;

    @Override
    public String toString() {
        return "Id: " + id + "\n" +
                "Order Date: " + orderDate + "\n" +
                "Delivery Date: " + deliveryDate + "\n" +
                "Products: " + productList + "\n" +
                "Customer: " + customer + "\n";
    }

    public static void generateOrders(List<Order> orderList, List<Product> productList, List<Customer> customerList) {
        Random random = new Random();
        String[] statusList = {"Ordered" , "Confirmed", "Received" , "Canceled"};
        LocalDate orderDate = LocalDate.now();
        LocalDate deliveryDate = LocalDate.now().plusDays(7);

        orderDate = generateOrderDate();
        deliveryDate = generateDeliveryDate(orderDate);
        LocalDate finalOrderDate = orderDate;
        LocalDate finalDeliveryDate = deliveryDate;


        Stream.generate(() -> new Order(
                current_id++,
                statusList[new Random().nextInt(statusList.length)],
                finalOrderDate,
                finalDeliveryDate,
                getRandomProductList(productList),
                getRanodmCustomer(customerList)))
                .limit(100)
                .forEach(orderList::add);
    }

    private static Customer getRanodmCustomer(List<Customer> customersList) {
        return customersList.get(
                new Random().nextInt(customersList.size())
        );
    }

    private static List<Product> getRandomProductList(List<Product> productList) {
        int numberOfProducts = new Random().nextInt(productList.size() / 10 + 1);

        return IntStream.range(0, numberOfProducts)
                .mapToObj(i -> productList.get(new Random().nextInt(productList.size())))
                .collect(Collectors.toList());
    }

    private static LocalDate generateDeliveryDate(LocalDate orderDate) {
        return orderDate.plusDays(new Random().nextInt(14));
    }

    private static LocalDate generateOrderDate() {
        return LocalDate.now().minusDays(new Random().nextInt(7));
    }
}
