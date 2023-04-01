package combinatorpattern;

import java.time.LocalDate;
import java.time.Period;
import java.util.function.Function;
import static combinatorpattern.CustomerRegistrationValidator.ValidationResult;
import static combinatorpattern.CustomerRegistrationValidator.ValidationResult.*;


public interface CustomerRegistrationValidator
        extends Function<Customer, ValidationResult> {

    static CustomerRegistrationValidator isEmailValid(){
        return customer -> customer.getEmail().contains("@") ?
                SUCCESS : EMAIL_NOT_VALID;
    }
    static CustomerRegistrationValidator isPhoneNumberValid(){
        return customer -> customer.getPhoneNumber().startsWith("05") ?
                SUCCESS : PHONE_NUMBER_NOT_VALID;
    }
    static CustomerRegistrationValidator isAdult(){
        int ADULT_MIN_AGE = 16;
        return customer ->
                Period.
                between(customer.getDob(), LocalDate.now()).
                getYears() > ADULT_MIN_AGE ?
                        SUCCESS : NOT_AN_ADULT;
    }

    default  CustomerRegistrationValidator and (CustomerRegistrationValidator other){
        return customer ->{
            ValidationResult result = this.apply(customer);
            return result.equals(SUCCESS) ? other.apply(customer) : result;
        };
    }

    enum ValidationResult{
        SUCCESS,
        PHONE_NUMBER_NOT_VALID,
        EMAIL_NOT_VALID,
        NOT_AN_ADULT
    }
}
