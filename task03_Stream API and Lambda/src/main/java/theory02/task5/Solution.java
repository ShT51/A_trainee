package theory02.task5;

import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 * Write a lambda expression that accepts a list of strings and returns new list of distinct strings (without repeating).
 * The order of elements in the result list may be any (elements will be sorted by the testing system).
 *
 * If the input list doesn't contain any strings then the result list should be empty.
 *
 * Hints: it is possible to use sets, maps, lists and so on for helping.
 */

public class Solution {

    public static void main(String[] args) {
        DistinctStrings<String> distStr = list -> list.stream().distinct().collect(toList());

        List<String> src = Arrays.asList("java", "scala", "java", "clojure", "clojure");
        List<String> out = distStr.removeRepeats(src);

        src.forEach(System.out::println);
        System.out.println("==========");
        out.forEach(System.out::println);
    }
}

@FunctionalInterface
interface DistinctStrings<T extends String> {
    List<T> removeRepeats(List<T> list);
}
