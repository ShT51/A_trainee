package theory02.task4;

import java.util.stream.LongStream;

/**
 * Write a lambda expression that accepts two long arguments as a range borders and
 * calculates (returns) production of all numbers in this range (inclusively).
 * It's guaranteed that 0 <= left border <= right border.
 * If left border == right border then the result is any border.
 */
public class Solution {

    public static void main(String[] args) {
        ProductionInRange<Long> prod = (l, r) ->
                LongStream.rangeClosed(l, r).reduce(1L, (acc, x) -> acc * x);

        System.out.println(prod.multiplyFromTo(0L, 1L));
        System.out.println(prod.multiplyFromTo(2L, 2L));
        System.out.println(prod.multiplyFromTo(1L, 4L));
        System.out.println(prod.multiplyFromTo(5L, 15L));
    }
}

@FunctionalInterface
interface ProductionInRange<T> {
    T multiplyFromTo(T l, T r);
}