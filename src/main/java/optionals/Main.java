package optionals;

import java.sql.SQLOutput;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        Optional.ofNullable("david@gmail.com") // check an item that can be null
                .ifPresentOrElse( // if not null(is present)
                    email -> System.out.println("Send email to " + email),
                        () -> System.out.println("Cannot send email") // else d this
        );

        Optional.ofNullable(null) // check an item that can be null
                .ifPresentOrElse( // if not null(is present)
                        email -> System.out.println("Send email to " + email),
                        () -> System.out.println("Cannot send email") // else d this
                );
    }
}
