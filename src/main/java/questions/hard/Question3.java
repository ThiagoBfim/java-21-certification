package questions.hard;

import java.util.List;

public class Question3 {

    public static void main(String[] args) {

        List.of(2, 4, 5, 6, 7, 8, 9)
                .stream()
                .takeWhile(n -> n < 5)
                .dropWhile(n -> n % 2 != 0)
                .forEach(System.out::print);

        System.out.println();
        int sum = List.of(1, 1, 2, 3, 4, 4, 5, 5, 5)
                .parallelStream()
                .distinct()
                .sorted()
                .reduce(0, (a, b) -> a + b);

        System.out.println("Sum = " + sum);
    }

}
