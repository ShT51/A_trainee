package theory03.task11.task02;

import java.util.function.Consumer;

public class Solution {

    public static void main(String[] args) {
        Consumer<Integer> printer = System.out::println;
        Consumer<Integer> devNull = (val) -> { int v = val * 2; };

        Consumer<Integer> combinedConsumer = devNull.andThen(devNull.andThen(printer));
        combinedConsumer.accept(100);

        // Consumer devNull doesn't change its argument and return nothing.
        System.out.println(combinedConsumer);
    }
}
