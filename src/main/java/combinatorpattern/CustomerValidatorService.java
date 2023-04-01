package combinatorpattern;

import java.time.LocalDate;
import java.time.Period;

public class CustomerValidatorService {

    public boolean isEmailValid(String email){
        return email.contains("@");
    }
    public boolean isPhoneNumberValid(String phoneNumber){
        return phoneNumber.startsWith("05");
    }
    public boolean isAdult(LocalDate dob){
        int ADULT_MIN_AGE = 16;
        return Period.
                between(dob, LocalDate.now()).
                getYears() > ADULT_MIN_AGE;
    }

    public boolean isValid(Customer customer){
        return isEmailValid(customer.getEmail())
                && isPhoneNumberValid(customer.getPhoneNumber())
                && isAdult(customer.getDob());
    }
}
