package theory08.task35;

import java.util.function.BiFunction;
import java.util.function.IntBinaryOperator;
import java.util.stream.IntStream;

/**
 * Write three operators:
 * <p>
 * 1. A reduce operator that accepts an initial value (seed) and a combiner function and then returns
 * a new function that combines all values in the given integer range (inclusively) into one integer value
 * (it's a simple form of reduction).
 * 2. In terms of the reduce operator define a sum operator for summing integer values in the given range.
 * 3 .In terms of the reduce operator define a product operator for multiplying integer values in the given range.
 * <p>
 * Try not to use Stream API. Write the reducer yourself.
 * <p>
 * To simplify the problem all functions are declared, you need to finish their realization.
 * Look carefully at definition of each operator.
 * <p>
 * During testing all three operators will be tested.
 * The left boundary <= the right boundary.
 * <p>
 * Example 1. Left boundary = 1, right boundary = 4.
 * * sumOperator returns the result 10.
 * * productOperator returns the result 24.
 * Example 2. Left boundary = 5, right boundary = 6.
 * * sumOperator returns the result 11.
 * * productOperator returns the result 30.
 */
public class Solution {

    /**
     * The operator combines all values in the given range into one value
     * using combiner and initial value (seed)
     */
    public static final BiFunction<Integer, IntBinaryOperator, IntBinaryOperator> reduceIntOperator =
            // without Stream API
            (seed, combiner) -> (left, right) -> {
                int acc = seed;
                for (int i = left; i <= right; i++) {
                    acc = combiner.applyAsInt(acc, i);
                }
                return acc;
            };

            // with Stream API
            // (seed, combiner) -> (left, right) -> IntStream.rangeClosed(left, right).reduce(seed, combiner);
    /**
     * The operator calculates the sum in the given range (inclusively)
     */
    public static final IntBinaryOperator sumOperator =
            reduceIntOperator.apply(0, Integer::sum);

    /**
     * The operator calculates the product in the given range (inclusively)
     */
    public static final IntBinaryOperator productOperator =
            reduceIntOperator.apply(1, (x, y) -> x * y);


    public static void main(String[] args) {
        System.out.println(sumOperator.applyAsInt(1, 4));
        System.out.println(productOperator.applyAsInt(1, 4));

        System.out.println(sumOperator.applyAsInt(5, 6));
        System.out.println(productOperator.applyAsInt(5, 6));
    }

}
