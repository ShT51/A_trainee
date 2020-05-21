package theory04.task18;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Create a stream that will detect bad words in a text according to a bad words list.
 * All words in the text are divided by whitespaces (always only one whitespace between two words).
 * <p>
 * After calling collect(Collectors.toList()) the stream must return the list of bad words which were found in the text.
 * The result should be dictionary ordered (in lexicographical order, i.e. sorted) and bad words shouldn't repeat.
 * <p>
 * Important. You need return a prepared stream from the template method, not a list of bad words.
 * Pay attention to the method template. Do not change it!
 */
public class Solution {

    private static List<String> badWords = Arrays.asList("*badWord1*", "*badWord2*", "*badWord*", "*badWord*");
    private static final String TEXT = "bla1 *badWord1* bla2 *badWord2* bla3 *badWord*";

    public static Stream<String> createBadWordsDetectingStream(String text, List<String> badWords) {
        return Arrays.stream(text.split("\\s"))
                .filter(badWords::contains)
                .sorted()
                .distinct();
    }

    public static void main(String[] args) {
        createBadWordsDetectingStream(TEXT, badWords).collect(Collectors.toList()).forEach(System.out::println);
    }

}
