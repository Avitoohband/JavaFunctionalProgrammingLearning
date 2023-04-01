package functionalinterface;

import java.util.function.Supplier;

public class _Supplier {

    public static void main(String[] args) {
        System.out.println("// Normal function way");
        System.out.println(getDBConnection());

        System.out.println("// Using Supplier");
        System.out.println(getDBConnectionSupplier.get());

    }
    static String getDBConnection(){
        return "jdbc://localhost:5432/users";
    }

    // Represents a supplier of results(returns a connection to DB for instance).
    static Supplier<String> getDBConnectionSupplier =
            () -> "jdbc://localhost:5432/users";
}
