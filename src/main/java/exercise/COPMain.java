package exercise;

import exercise.entity.Customer;
import exercise.entity.Order;
import exercise.entity.Product;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class COPMain {

    public static void main(String[] args) {
        List<Customer> customerList = new ArrayList<>();
        List<Product> productList = new ArrayList<>();
        List<Order> orderList = new ArrayList<>();

        Customer.generateCustomers(customerList);
        Product.generateProducts(productList);
        Order.generateOrders(orderList, productList, customerList);

        // Exercise 0 - Order by Desc Date
        List<Order> tenOrdersOrdersByOrderDateDescending = orderList.stream()
                .limit(30).
                sorted(Comparator.comparing(Order::orderDate).reversed())
                .toList();

        // Exercise 1 - Obtain a list of products belongs to category “Books” with price > 100
        List<Product> booksCategoryProductList = productList.stream()
                .filter(Product::isBooksCategoryAndOver100)
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
                .map(Product::applyTenPercent)
                .toList();

        // Exercise 4 — Obtain a list of products ordered by customer of tier 2 between 01-Apr-2023 and 10-Apr-2021
        List<Product> customerTierTwoAndBetweenDates = orderList.stream()
                .filter(o -> o.customer().tier() == 2)
                .filter(o -> o.orderDate().isAfter(LocalDate.of(2023, 4, 1)))
                .filter(o -> o.orderDate().isBefore(LocalDate.of(2023, 4, 10)))
                .flatMap(o -> o.productList().stream())
                .distinct()
                .toList();

        // Exercise 5 — Get the cheapest products of “Books” category
        Product cheapestProductBookCategory = productList.stream()
                .filter(p -> "Books".equalsIgnoreCase(p.category()))
                .min(Comparator.comparing(Product::price))
                .orElse(null);

//        List<Product> productBookCategoryOrderedByPriceList = productList.stream()
//                .filter(p -> "Books".equalsIgnoreCase(p.category()))
//                        .sorted(Comparator.comparingDouble(Product::price))
//                                .toList();

        // Exercise 6 — Get the 3 most recent placed order
        List<Order> threeMostRecent = orderList.stream()
                .sorted(Comparator.comparing(Order::orderDate).reversed())
                .limit(3)
                .toList();

        //Exercise 7 — Get a list of orders which were ordered on a certain date,
        // log the order records to the console and then return its product list
        List<Product> productOrderedByCertainDateSoutOrder = orderList.stream()
                .filter(o -> o.orderDate().equals(LocalDate.now().minusDays(5)))
                .peek(System.out::println)
                .flatMap(o -> o.productList().stream())
                .distinct().toList();

        // Exercise 8 — Calculate total lump sum of all orders placed on a certain date
        Double sumTotalPriceOrdersOnCertainDate = orderList.stream()
                .filter(o -> o.orderDate().equals(LocalDate.now().minusDays(5)))
                .flatMap(o -> o.productList().stream())
                .mapToDouble(Product::price)
                .sum();

        // Exercise 9 — Calculate order average payment placed on a certain date
        Double averageTotalPriceOrdersOnCertainDate = Product.formatPrice(
                orderList.stream()
                        .filter(o -> o.orderDate().equals(LocalDate.now().minusDays(5)))
                        .flatMap(o -> o.productList().stream())
                        .mapToDouble(Product::price)
                        .average()
                        .orElse(0)
        );

        // Exercise 10 — Obtain a collection of statistic figures (i.e. sum, average, max, min, count)
        // for all products of category “Books”
        DoubleSummaryStatistics statistics = productList.stream()
                .filter(p -> "Books".equalsIgnoreCase(p.category()))
                .mapToDouble(Product::price)
                .summaryStatistics();
        //Product::formatPrice;

        // Exercise 11 — Obtain a data map with order id and order’s product count
        Map<Long, Integer> orderIdAmountProductsMap = orderList.stream()
                .collect(Collectors.toMap(
                        Order::id,
                        o -> o.productList().size()
                ));

        // Exercise 12 — Produce a data map with order records grouped by customer
        Map<Customer, List<Order>> ordersGroupedByCustomers = orderList.stream()
                .collect(Collectors.groupingBy(Order::customer));


        // Exercise 16 — Obtain a map which hold as value orderDate of the order, and the value holds the amount of orders of this date
        Map<LocalDate, Long> orderDatesAmountOrdersMap = orderList.stream()
                .collect(Collectors.groupingBy(Order::orderDate, Collectors.counting()));

        // Exercise 17 — Obtain a map which hold as value orderDate of the order, and the value holds the amount of products of this date
        Map<LocalDate, Integer> orderDatesAmountProductsMap = orderList.stream()
                .collect(Collectors.groupingBy(Order::orderDate, Collectors.summingInt(o -> o.productList()
                        .size())));

        // Exercise 13 — Produce a data map with order record and product total sum
        Map<Order, Double> orderMapSumPriceOfProducts = orderList.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        o -> Product.formatPrice(o.productList().stream().mapToDouble(Product::price).sum())
                ));

        // Exercise 14 — Obtain a data map with list of product name by category
        Map<String, List<String>> productsGroupedByCategoryMap = productList.stream()
                .collect(Collectors.groupingBy(
                        Product::category, Collectors.mapping(Product::name, Collectors.toList())
                ));

        // Exercise 15 — Get the most expensive product by category
        Map<String, Optional<Product>> mostExpensiveProductByCategory = productList.stream()
                .collect(
                        Collectors.groupingBy(
                                Product::category,
                                Collectors.maxBy(Comparator.comparing(Product::price))
                        ));
    }


}