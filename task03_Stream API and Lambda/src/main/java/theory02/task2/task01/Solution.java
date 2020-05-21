package theory02.task2.task01;

/**
 * Write a lambda expression that accepts two integers arguments and returns max of them.
 *
 * Try not to use the Math library.
 */
public class Solution {

    public static void main(String[] args) {
        FindMax<Integer> findMax = (x, y) -> x >= y ? x : y;
        System.out.println(findMax.showMax(50, -5));
    }

}

@FunctionalInterface
interface FindMax<T> {
    T showMax(T x, T y);
}