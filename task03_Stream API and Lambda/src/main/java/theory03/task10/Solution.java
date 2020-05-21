package theory03.task10;

/**
 * You need to write your own functional interface (TernaryIntPredicate) and use it with a lambda expression.
 *
 * The interface must have a single non-static (and non-default) method test with three int arguments that returns boolean value.
 *
 * Besides, you need to write a lambda expression with three int arguments using your TernaryIntPredicate.
 *
 * The lambda expression has to return true if all passed values are different otherwise false.
 *
 * The name of the instance is allValuesAreDifferentPredicate. It should be a static field.
 */


public class Solution {
    public static final TernaryIntPredicate allValuesAreDifferentPredicate = ((x, y, z) -> x != y && y != z && x != z);

    public static void main(String[] args) {
        System.out.println(allValuesAreDifferentPredicate.test(4, 3, 2));
        System.out.println(allValuesAreDifferentPredicate.test(4, 3, 3));
        System.out.println(allValuesAreDifferentPredicate.test(2, 3, 2));
        System.out.println(allValuesAreDifferentPredicate.test(2, 2, 2));
        System.out.println(allValuesAreDifferentPredicate.test(2, 3, 4));
    }
}

@FunctionalInterface
interface TernaryIntPredicate {
    boolean test(int x, int y, int z);
}