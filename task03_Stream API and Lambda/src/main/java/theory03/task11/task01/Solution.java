package theory03.task11.task01;

import java.util.function.IntUnaryOperator;

public class Solution {

    public static void main(String[] args) {
        IntUnaryOperator mult2 = num -> num * 2;
        IntUnaryOperator add3 = num -> num + 3;

        IntUnaryOperator combinedOperator = add3.compose(mult2.andThen(add3)).andThen(mult2);
        int result = combinedOperator.applyAsInt(5);

        // (3 + (5 * 2 + 3)) * 2 = 32
        // (1) mult2.andThen(add3) = ((5*2) + 3) = 13
        // (2) add3.compose(...) = (13 + 3)
        // (3) andThen(mult2) = (16 * 2)
        System.out.println(result);
    }
}
