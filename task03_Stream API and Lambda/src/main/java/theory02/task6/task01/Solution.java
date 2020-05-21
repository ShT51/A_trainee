package theory02.task6.task01;

/**
 * Using closure write a lambda expression that calculates a*x^2 + b*x + c
 * where a, b, c are context final variables.
 * They will be available in the context during testing. Note, the result is double.
 */

public class Solution {

    public static void main(String[] args) {
        final double a = 2d;
        final double b = 3d;
        final double c = 4d;
        Polynomial<Long, Double> equation = x -> (a * x * x + b * x + c);

        System.out.println(equation.findX(2L));
        System.out.println(equation.findX(0L));
    }
}

@FunctionalInterface
interface Polynomial<T, Double> {
    Double findX(T x);
}