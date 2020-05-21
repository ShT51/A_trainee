package theory03.task12;

import java.util.List;
import java.util.function.IntPredicate;

/**
 * Write the disjunctAll method that accepts a list of IntPredicate's and returns a single IntPredicate.
 * The result predicate is a disjunction of all input predicates.
 * <p>
 * If the input list is empty then the result predicate should return false for any integer value (always false).
 */
public class Solution {

    public static IntPredicate disjunctAll(List<IntPredicate> predicates) {
        return predicates.stream().reduce(x -> false, IntPredicate::or);
    }
}
