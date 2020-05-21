package theory04.task17;

import java.util.stream.LongStream;

/**
 * Write a method using Stream API to check the input number is prime or not.
 * Let's agree that input value is always greater than 1 (i.e. 2, 3, 4, 5, ....).
 * Use the provided template for your method.
 * <p>
 * A prime number is a value greater than 1 that has no positive divisors other than 1 and itself.
 * See https://en.wikipedia.org/wiki/Prime_number
 */
public class Solution {

    /**
     * Checking if a number is prime
     *
     * @param number to test >= 2
     * @return true if number is prime else false
     */
    public static boolean isPrime(final long number) {
        // write your code here
        return LongStream
                .range(2, (number >> 1) + 1)
                .noneMatch(i -> number % i == 0);
    }

    public static void main(String[] args) {
        System.out.println(isPrime(2L));
        System.out.println(isPrime(3L));
        System.out.println(isPrime(4L));
        System.out.println(isPrime(5L));
    }
}
