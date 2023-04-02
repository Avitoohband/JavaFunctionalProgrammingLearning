package exercise;

import exercise.entity.Customer;
import exercise.entity.Order;
import exercise.entity.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class COPMain {

    public static void main(String[] args) {
        List<Customer> customerList = new ArrayList<>();
        List<Product> productList = new ArrayList<>();
        List<Order> orderList = new ArrayList<>();

        Customer.generateCustomers(customerList);
        Product.generateProducts(productList);
        Order.generateOrders(orderList, productList, customerList);

        List<Order> tenOrdersOrdersByOrderDate = orderList.stream()
                .limit(10).
                sorted(Comparator.comparing(Order::orderDate))
                .toList();

        System.out.println();
    }
}
