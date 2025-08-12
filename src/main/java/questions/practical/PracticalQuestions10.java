package questions.practical;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class PracticalQuestions10 {


    public static void main(String[] args) {

        Stream<Double> generate = Stream.generate(() -> ThreadLocalRandom.current().nextDouble());
        generate
//                .sorted() //infinite loop
                .limit(2)
                .filter(i -> i > 0)
                .forEach(System.out::println);
    }
}

class OptionalTest {

    public static void main(String[] args) {
        Optional<String> optional = Optional.of("Hello");
        System.out.println(optional.orElse("World")); //Hello
        Optional<String> optional2 = Optional.empty();
        optional2.of("Error"); //Optional.of() from static, this creates another Optional
        System.out.println(optional2.orElse("World")); //World
    }
}

class Reduce {

    public static void main(String[] args) {

        System.out.println("""
                In a sequential (i.e. no-parallel) stream, the identity value will be used only one (to reduce the first element) 
                but a parallel stream may internally be split into multiple substreams and the first element of each substream will be reduced using the identity value. 
                Therefore, the final value (which will be determined by reducing the values produced by each substream) will depend on how many substream were created.
                """);

        List<Integer> list = Arrays.asList(2, 3, 4, 5, 9, 7, 8);
        Integer x1 = list.stream().reduce(0, (a, b) -> a > b ? a : b); //9
        Integer x2 = list.stream().reduce((a, b) -> a > b ? a : b).get(); //9
        Integer x3 = list.stream().max((a, b) -> a > b ? a : b).get(); //2 -> Expects a comparator, in this case 2 is considered bigger. -1 (minor) ,0 (equal),1 (bigger)
        Integer x4 = list.stream().max(Integer::compare).get(); //9
        Integer x5 = list.stream().max(Integer::max).get(); //2
        Integer x6 = list.stream().mapToInt(i -> i).max().getAsInt(); //9

        System.out.println(x1);
        System.out.println(x2);
        System.out.println(x3);
        System.out.println(x4);
        System.out.println(x5);
        System.out.println(x6);

        List<String> items = Arrays.asList("Candy", "Lollipop");

        var result = items.parallelStream()
                .reduce(items.parallelStream().reduce((a2, b) -> a2 + "1").get(), //Candy1
                        (a2, b) -> a2 + b);

        System.out.println(result); //Candy1CandyCandy1Lollipop
    }
}

class TakeWhile {

    public static void main(String[] args) {
        List<Integer> integers = List.of(0, 2, 4, 6, 8, 9, 10, 11, 12);
        List<Integer> list1 = integers
                .stream()
                .takeWhile(it -> it % 2 == 0) //0,2,4,6,8 -> takeWhile stop on the first false. Take while will retrieve all the elements that matches with the predicate
                .dropWhile(it -> {
//                    boolean result = it < 4; //true,true,false [4,6,8]
                    boolean result = it > 4; //false [0] - stop, remove the true, in this case no one
                    System.out.println(result);
                    return result; //DropWhile stop on the first false result. Drop while will remove all the elements that matches with the predicate
                }) //stop on the first false.
                .toList(); //[0, 2, 4, 6, 8]

        System.out.println(list1); //[0, 2, 4, 6, 8]


        List<Integer> integers2 = List.of(8, 10, 0, 2, 4, 6, 8, 9, 10, 11, 12);
        List<Integer> list2 = integers2
                .stream()
                .takeWhile(it -> it % 2 == 0) //8,10,0,2,4,6,8 -> takeWhile stop on the first false. Take while will retrieve all the elements that matches with the predicate
                .dropWhile(it -> {
//                    boolean result = it < 4; //false [8,10,0,2,4,6,8]
                    boolean result = it > 4;  //true,true,false [8,10,0]
                    System.out.println(result);
                    return result; //DropWhile stop on the first false result. Drop while will remove all the elements that matches with the predicate
                }) //stop on the first false.
                .toList(); //[0, 2, 4, 6, 8]
        System.out.println(list2); //[0, 2, 4, 6, 8]
    }
}

class StreamSorted {

    /**
     * The Stream does not keep the sorted order after a forEach. Use a Collectors or forEachOrdered
     *
     * @param args
     */
    public static void main(String[] args) {

        var list = List.of("1", "-1", "0", "ab", "ABC", "def", "def");

        list.stream().sorted().map(s -> s + " ").forEach(System.out::print); //-1 0 1 ABC ab def def
        System.out.println();
        list.stream().parallel().distinct().sorted().map(s -> s + " ").forEach(System.out::print); //ab ABC -1 1 def 0  ?? The result is not guaranteed, however the distinct is always applied
        List<String> result = list.stream().parallel().distinct().sorted().toList();
        System.out.println();
        System.out.println(result); //[-1, 0, 1, ABC, ab, def] //Sorted values

    }
}