package theory08.task33.task01;

import java.util.function.IntFunction;

/**
 * Write a curried form of the function f(x, y, z) = x + y * y + z * z * z using lambda expressions in Java 8 style.
 * The result and x, y, z must be integer numbers.
 * <p>
 * Solution format. You may write the result in any valid formats but with ; on the end.
 * <p>
 * An example of a curried function: x -> y -> ...;
 */
public class Solution {
    static IntFunction<IntFunction<IntFunction<Integer>>> xyz = x -> y -> z -> x + y * y + z * z * z;

    public static void main(String[] args) {
        System.out.println(xyz.apply(1).apply(2).apply(3));
    }
}
