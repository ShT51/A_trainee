package theory02.task2.task02;

/**
 * Write a lambda expression that accepts a long value and returns a next even number.
 *
 * It is guaranteed an input number is non-negative.
 */
public class Solution {

    public static void main(String[] args) {
        FindNextEvenNumber<Long> evenNumber = x -> x + (2 - x % 2);
        System.out.println(evenNumber.nexEven(317L));
        System.out.println(evenNumber.nexEven(2L));
    }
}

@FunctionalInterface
interface FindNextEvenNumber<T> {
    T nexEven(T x);
}