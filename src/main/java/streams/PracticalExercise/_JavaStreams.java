package streams.PracticalExercise;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class _JavaStreams {
    public static void main(String[] args) throws IOException {
        // 1. Integer Stream
        IntStream
                .range(1, 10)
                .forEach(System.out::println);
        System.out.println();

        // 2. Integer Stream with skip\
        IntStream
                .range(1, 10)
                .skip(5) // skip all ints that are <= 5
                .forEach(num -> System.out.println(num));
        System.out.println();

        // 3. Integer Stream with sum
        System.out.println(IntStream
                .range(1, 5)
                .sum());
        System.out.println();

        // 4. Stream.of, sorted and findFirst
        Stream.of("Ava", "Aneri", "Alberto")
                .sorted()
                .findFirst()
                .ifPresent(System.out::println);
        System.out.println();

        // 5. Stream from Array, sort, filter and print
        String[] names = {"Al", "Tom", "Joey", "Alex", "Shelly", "Shiva", "Salamandra"};
        Arrays.stream(names)
                .filter(name -> name.startsWith("S"))
                .sorted()
                .forEach(System.out::println);

        // 6. Average of squares of an int array
        Arrays.stream(new int[]{5, 1, 2, 4, 6, 8, 4})
                .map(num -> num * num)
                .average()
                .ifPresent(System.out::println);
        System.out.println();

        // 7. Stream from List, filter and print
        List<String> people = Arrays.asList(names);
        people
                .stream()
                .map(name -> name.toLowerCase())
                .filter(name -> name.startsWith("a"))
                .forEach(System.out::println);
        System.out.println();

        // 8. Stream rows from text file, sort, filter, and print
        Stream<String> bands = Files.lines(Paths.get("src/main/java/streams/PracticalExercise/bands.txt"));
        bands
                .sorted()
                .filter(band -> band.length() > 13)
                .forEach(System.out::println);
        bands.close();
        System.out.println();


        // 9. Stream rows from text file and save to List
        List<String> bands2 = Files.lines(Paths.get("src/main/java/streams/PracticalExercise/bands.txt"))
                .filter(band -> band.contains("jit"))
                .collect(Collectors.toList());
        bands2.forEach(System.out::println);
        System.out.println();

        // 10. Stream rows from CVS file and count
        Stream<String> rows1 = Files.lines(Paths.get("src/main/java/streams/PracticalExercise/data.txt"));
        int rowCount = (int) rows1
                .map(data -> data.split(","))
                .filter(splited -> splited.length == 3)
                .count();
        System.out.println(rowCount + " rows.");
        rows1.close();
        System.out.println();

        // 11. Stream rows from CSV file, parse data from rows
        Stream<String> rows2 = Files.lines(Paths.get("src/main/java/streams/PracticalExercise/data.txt"));
        rows2
                .map(data -> data.split(","))
                .filter(splited -> splited.length == 3)
                .filter(validLengthRow -> Integer.parseInt(validLengthRow[1]) > 15)
                .forEach(biggerThan15 -> System.out.println(
                                biggerThan15[0] + " " +
                                        biggerThan15[1] + " " +
                                        biggerThan15[2]
                        )
                );
        rows2.close();
        System.out.println();

        // 12. Stream rows from CSV file, store fields in HashMap
        Stream<String> rows3 = Files.lines(Paths.get("src/main/java/streams/PracticalExercise/data.txt"));
        Map<String, Integer> map = new HashMap<>();
        map = rows3
                .map(data -> data.split(","))
                .filter(splited -> splited.length == 3)
                .filter(validLengthRow -> Integer.parseInt(validLengthRow[1]) > 15)
                .collect(Collectors.toMap(
                        key -> key[0],
                        value -> Integer.parseInt(value[1])
                ));
        rows1.close();

        for (String key : map.keySet()) {
            System.out.println(key + " " + map.get(key));
        }
        System.out.println();

        // 13. Reduction - sum
        double total = Stream.of(7.3, 1.5, 4.8)
                .reduce(0.0, (Double a, Double b) -> a + b);
        // reduce(0.0, Double::sum) could be like this as well
        System.out.println("Total = " + total);
        System.out.println();

        // 14. Reductions - summary statistics
        IntSummaryStatistics summary = IntStream.of(7, 2, 19, 88, 73, 4, 10)
                .summaryStatistics(); // will give
        // the count, total sum, min val, average, and max value
        System.out.println(summary);
        System.out.println();


    }
}
