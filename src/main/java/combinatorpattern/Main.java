package combinatorpattern;

import java.time.LocalDate;

import static combinatorpattern.CustomerRegistrationValidator.*;

public class Main {

    public static void main(String[] args) {
        Customer customer = new Customer(
                "Alice",
                "alic@egmail.com",
                "05023424523",
                LocalDate.of(2004, 5, 3)
        );
        CustomerValidatorService validService = new CustomerValidatorService();
        System.out.println(validService.isValid(customer));

        // If valid we can store customer in db

        System.out.println("// Using Combinator Pattern");
        ValidationResult result = isEmailValid()
                .and(isPhoneNumberValid()
                .and(isAdult()))
                .apply(customer);
        System.out.println(result);
        if(result != ValidationResult.SUCCESS){
            throw new IllegalStateException(result.name());
        }


    }
}
