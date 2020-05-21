package theory08.task33.task02;

import java.util.function.Function;

/**
 * Write a curried function (using lambdas) that accepts four string arguments and
 * concatenated all in one string following the rules:
 * <p>
 * * String cases: in the result string,
 * first and second arguments must be in lower cases and third and fourth in upper cases.
 * * Order of arguments concatenation: first, third, second, fourth.
 * <p>
 * Solution format. You may write the result in any valid formats but with ; on the end.
 * <p>
 * An example of a curried function: x -> y -> ...;
 */
public class Solution {
    static Function<String, Function<String, Function<String, Function<String, String>>>> concat =
            arg1 -> arg2 -> arg3 -> arg4 ->
                    arg1.toLowerCase() +
                    arg3.toUpperCase() +
                    arg2.toLowerCase() +
                    arg4.toUpperCase();

    public static void main(String[] args) {
        System.out.println(concat.apply("aaa").apply("bbb").apply("ccc").apply("ddd"));
    }
}
