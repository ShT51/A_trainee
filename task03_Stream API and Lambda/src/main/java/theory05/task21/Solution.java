package theory05.task21;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Write a method for calculating the sum of odd numbers in the given interval (inclusively) using Stream API.
 */
public class Solution {
    /**
     * The method calculates the sum of odd numbers in the given range
     *
     * @param start of a range, start >= 0
     * @param end   of a range (inclusive), end >= start
     * @return sum of odd numbers
     */
    public static long sumOfOddNumbersInRange(long start, long end) {
        // write your code here
        return LongStream.rangeClosed(start, end)
                .filter(n -> n % 2 != 0)
                .sum();
    }

    public static void main(String[] args) {
        System.out.println(sumOfOddNumbersInRange(2, 2));
        System.out.println(sumOfOddNumbersInRange(0, 5));
        System.out.println(sumOfOddNumbersInRange(7, 7));
    }
}
