package functionalinterface;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class _Consumer {

    public static void main(String[] args) {
        Customer customerExample = new Customer("David", "0525252524");

        System.out.println("// Normal Java way");
        greetCustomer(customerExample);

        System.out.println("// Using Consumer");
        greetCustomerConsumer.accept(customerExample);

        System.out.println(" // Using BiConsumer to show or hide phone number");
        greetCustomerBiConsumer.accept(customerExample, false);
        greetCustomerBiConsumer.accept(customerExample, true);


    }

    // Represents an operation that accepts a single input argument and returns no result.
    static Consumer<Customer> greetCustomerConsumer =
            customer -> {
                System.out.printf("Hello %s, phone number is %s%n", customer.name, customer.phone);
            };


    // Represents an operation that accepts two input arguments and returns no result.
    static BiConsumer<Customer, Boolean> greetCustomerBiConsumer =
            (customer, showPhone) ->
                    System.out.printf("Hello %s, phone number is %s%n",
                            customer.name, showPhone ? customer.phone : "**********");


    static void greetCustomer(Customer customer) {
        System.out.printf("Hello %s, phone number is %s%n", customer.name, customer.phone);
    }

    static class Customer {
        private final String name;
        private final String phone;

        public Customer(String name, String phone) {
            this.name = name;
            this.phone = phone;
        }

    }
}
