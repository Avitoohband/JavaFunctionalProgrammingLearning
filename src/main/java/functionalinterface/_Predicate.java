package functionalinterface;

import java.util.function.Predicate;

public class _Predicate {

    public static void main(String[] args) {
        String phoneNumber1 = "0506408540";
        String phoneNumber2 = "0106408540";
        String phoneNumber3 = "05064085401";

        System.out.println("// Without Predicate");
        System.out.println(isValidPhoneNumber(phoneNumber1));
        System.out.println(isValidPhoneNumber(phoneNumber2));
        System.out.println(isValidPhoneNumber(phoneNumber3));

        System.out.println("// With Predicate");
        System.out.println(isValidPhoneNumberPredicate.test(phoneNumber1));
        System.out.println(isValidPhoneNumberPredicate.test(phoneNumber2));
        System.out.println(isValidPhoneNumberPredicate.test(phoneNumber3));

    }

    static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.length() == 10 && phoneNumber.startsWith("05");
    }

    //Represents a predicate (boolean-valued function) of one argument.
    static Predicate<String> isValidPhoneNumberPredicate =
            phoneNumber -> phoneNumber.length() == 10 && phoneNumber.startsWith("05");

}



