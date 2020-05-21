package theory06.task26;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Write a collector that evaluates the product of squares of integer numbers in a Stream<Integer>.
 * <p>
 * Important. You should write only the collector in any valid formats but without ; on the end.
 * It will be passed as an argument to the collect() method of a stream as shown below.
 * <p>
 * List<Integer> numbers = ...
 * long val = numbers.stream().collect(...your_collector_will_be_passed_here...);
 */
public class Solution {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2);
        //long reduce = numbers.stream().mapToLong(Integer::longValue).reduce(1L, (acc, n) -> acc * n * n);
        long reduce = numbers.stream().collect(Collectors.reducing(1L, Integer::longValue, (a, b) -> a * b * b));
        System.out.println(reduce);
    }
}
