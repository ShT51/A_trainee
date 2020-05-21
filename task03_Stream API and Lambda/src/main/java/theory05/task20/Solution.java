package theory05.task20;

import java.util.stream.LongStream;

/**
 * Many java developers wrote methods to calculate a factorial value using a recursive or iterative algorithm.
 * It's time to do it with streams.
 * <p>
 * The factorial of n is calculated by the product of integer number from 1 to n (inclusive).
 * The factorial of 0 is equal to 1.
 * <p>
 * Important. Use the given template for your factorial method.
 * Pay attention to type of an argument and the returned value. Please, don't use cycles or recursion.
 */
public class Solution {

    /**
     * Calculates the factorial of the given number n
     *
     * @param n >= 0
     * @return factorial value
     */
    public static long factorial(long n) {
        // write your code here
        return LongStream
                .rangeClosed(2, n)
                .reduce(1, (acc, elem) -> acc * elem);
    }

    public static void main(String[] args) {
        System.out.println(factorial(0));
        System.out.println(factorial(1));
        System.out.println(factorial(3));
    }

}
