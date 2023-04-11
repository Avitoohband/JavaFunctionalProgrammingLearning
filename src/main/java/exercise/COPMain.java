package exercise;

import exercise.entity.Customer;
import exercise.entity.Order;
import exercise.entity.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class COPMain {

    public static void main(String[] args) {
        List<Customer> customerList = new ArrayList<>();
        List<Product> productList = new ArrayList<>();
        List<Order> orderList = new ArrayList<>();

        Customer.generateCustomers(customerList);
        Product.generateProducts(productList);
        Order.generateOrders(orderList, productList, customerList);

        // Order by Desc Date
        List<Order> tenOrdersOrdersByOrderDateDescending = orderList.stream()
                .limit(30).
                sorted(Comparator.comparing(Order::orderDate).reversed())
                .toList();

        // Obtain a list of products belongs to category “Books” with price > 100
        List<Product> booksCategoryProductList = productList.stream()
                .filter(COPMain::isBooksCategoryAndOver100)
                .toList();

        // Exercise 2 — Obtain a list of order with products belong to category “Baby”
        List<Order> babyProductsCategoryOrderList = orderList.stream()
                .filter(o -> o.productList().stream()
                        .anyMatch(
                                p -> p.category().equalsIgnoreCase("Baby"))
                )
                .toList();

        // Exercise 3 — Obtain a list of product with category = “Toys” and then apply 10% discount
        List<Product> toysWith10PerDiscount = productList.stream()
                        .filter(p -> "Toys".equalsIgnoreCase(p.category()))
                                .map(p -> p = p.withPrice(p.price() * 0.9)) // create method to format the double
                // also change the create Product to use that method
                .toList();

        System.out.println();
    }

    private static Boolean isBooksCategoryAndOver100(Product product) {
        return "Books".equalsIgnoreCase(product.category()) && (Objects.nonNull(product.price()) && product.price() > 100);
    }
}