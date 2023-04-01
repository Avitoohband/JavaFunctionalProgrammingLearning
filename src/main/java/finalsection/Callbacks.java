package finalsection;

import java.util.function.Function;

public class Callbacks {
    public static void main(String[] args) {
        hello("Avi", "Tuchband", checkLastName);
    }

    static void hello(String firstName,
                      String lastName,
                      Function<String, String> callback) {
        System.out.println(firstName + callback.apply(lastName));
    }

    static Function<String, String> checkLastName =
            lName -> lName == null ? "" : " " + lName;
}
