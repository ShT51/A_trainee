package theory06.task27;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Write a collector that partitions all words in a stream into two groups: palindromes (true) and usual words (false).
 * The collector should return Map<Boolean, List<String>>. All input words will be in the same case (lower).
 * <p>
 * Let's remind, a palindrome is a word or phrase which reads the same backward or forward, such as noon or level.
 * In our case, a palindrome is always a word. For details, see https://en.wikipedia.org/wiki/Palindrome
 * <p>
 * Hint: the method reverse() of StringBuilder may help you.
 * <p>
 * Important. You should write only the collector in any valid formats but without ; on the end.
 * It will be passed as an ﻿argument to the collect() method of a stream as shown below.
 * <p>
 * String[] words = ...
 * Map<Boolean, List<String>> palindromeOrNoMap =
 * Arrays.stream(words)
 * .collect(...your_collector_will_be_passed_here...);
 * <p>
 * Examples of the valid solution formats: Collectors.reducing(...) or reducing(...)
 */
public class Solution {
    public static void main(String[] args) {
        String[] words = {"level", "noon", "moon", "nan", "sun"};

        Map<Boolean, List<String>> palindromeOrNoMap = Arrays.stream(words)
                .collect(Collectors.partitioningBy(word ->
                        new StringBuilder(word).
                                reverse().toString().equalsIgnoreCase(word)
                ));

        palindromeOrNoMap.forEach((k, v) -> System.out.println(k + " : " + v));
    }

}
