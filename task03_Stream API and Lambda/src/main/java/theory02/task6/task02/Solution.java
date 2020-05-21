package theory02.task6.task02;

/**
 * Using closure write a lambda expression that adds prefix (before) and suffix (after) to its single string argument;
 * prefix and suffix are final variables and will be available in the context during testing.
 * All whitespaces on the both ends of the argument must be removed.
 * Do not trim prefix, suffix and the result string.
 */

public class Solution {

    public static void main(String[] args) {
        final String prefix = "[";
        final String suffix = "]";
        Framer<String> framer = str -> prefix + str.trim() + suffix;

        System.out.println(framer.frame("whitespace"));
    }
}

@FunctionalInterface
interface Framer<String> {
    String frame(String str);
}