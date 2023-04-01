package functionalinterface;

import java.util.function.BiFunction;
import java.util.function.Function;

public class _Function {
    public static void main(String[] args) {
        System.out.println("// The NO Functional approach");
        int incremented = incrementByOne(0);
        System.out.println(incremented);

        System.out.println("// The Functional approach");
        Integer incremented2 = incrementByOneFunction.apply(0);
        System.out.println(incremented2);

        System.out.println("// Apply and Then");
        Function<Integer, Integer> incByOneAndMulBy10
                = incrementByOneFunction.andThen(MulBy10Function);
        Integer inced1AndMuled10Function = incByOneAndMulBy10.apply(0);
        System.out.println(inced1AndMuled10Function);

        System.out.println("// BiFunctional, inc by 1 and mul by");
        System.out.println(incByOneAndMulByBiFunction.apply(1,20));
    }

    //Represents a function that accepts one argument and produces a result.
    static Function<Integer, Integer> incrementByOneFunction = number -> number + 1;
    static Function<Integer, Integer> MulBy10Function = number -> number * 10;

    //Represents a function that accepts two arguments and produces a result.
    static BiFunction<Integer, Integer, Integer> incByOneAndMulByBiFunction =
            (numToIncByOne, numToMulBy) -> (numToIncByOne + 1) * numToMulBy;

    static int incrementByOne(int number) {
        return number + 1;
    }

}
