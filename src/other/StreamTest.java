package other;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(20);
        list.add(5);
        List<Integer> list2 = list.stream()
                .filter(num -> num > 10)
                .collect(Collectors.toList());
        list2.forEach(System.out::println);
    }
}
