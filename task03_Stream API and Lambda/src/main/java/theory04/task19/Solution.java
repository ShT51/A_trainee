package theory04.task19;

import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * You have two IntStream.
 * The first stream contains even numbers and the second stream contains odd numbers.
 * Create the third stream that contains numbers from both streams which is divisible by 3 and 5.
 * After calling collect(Collectors.toList()) the stream should return sorted list (ascending) of these numbers.
 * Two first suitable numbers in the sorted list must be skipped.
 * <p>
 * Important. You need return a prepared IntStream from a template method, not a list of integers.
 * Pay attention to the method template. Do not change the signature of this method.
 */
public class Solution {

    public static IntStream createFilteringStream(IntStream evenStream, IntStream oddStream) {
        IntPredicate div3 = x -> x % 3 == 0;
        IntPredicate div5 = x -> x % 5 == 0;
        IntPredicate predicate = div3.and(div5);

        return IntStream.concat(evenStream, oddStream)
                .filter(predicate)
                .sorted()
                .skip(2);

        /*return IntStream.concat(evenStream, oddStream)
                .filter(x -> x % 15 == 0)
                .sorted()
                .skip(2);*/
    }

    public static void main(String[] args) {
        createFilteringStream(IntStream.of(2, 4, 8, 30, 60, 90), IntStream.of(3, 5, 15, 25, 75))
                .boxed()
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }
}
