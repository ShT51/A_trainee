package theory02.task3;

/**
 * Write a lambda expression that accepts seven (!) string arguments and returns
 * a string in upper case concatenated from all of them (in the order of arguments).
 *
 * The correct solution may not work in your local environment because you don't have suitable functional interface.
 * The testing system has the interface and can test your solution.
 */
public class Solution {

    public static void main(String[] args) {
        TooManyArgumentsInterface<String> superString = (a, b, c, d, e, f, g) -> (a + b + c + d + e + f + g).toUpperCase();
        System.out.println(superString.concat("the", "lambda", "has", "too", "many", "string", "arguments"));
    }
}

@FunctionalInterface
interface TooManyArgumentsInterface<T> {
    T concat(T a, T b, T c, T d, T e, T f, T g);
}