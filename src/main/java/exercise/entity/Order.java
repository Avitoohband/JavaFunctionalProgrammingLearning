package exercise.entity;

import java.time.LocalDate;
import java.util.List;

public record Order(Long id, String status, LocalDate orderDate, LocalDate deliveryDate, List<Product> productList, Customer customer) {

    @Override
    public String toString(){
        return "Id: " + id + "\n" +
                "Order Date: " + orderDate + "\n" +
                "Delivery Date: " +deliveryDate + "\n" +
                "Products: " + productList + "\n" +
                "Customer: " + customer + "\n";
    }
}
