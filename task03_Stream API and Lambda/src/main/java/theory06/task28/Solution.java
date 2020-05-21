package theory06.task28;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.summingLong;

/**
 * You have two classes:
 * <p>
 * Account: number: String, balance: Long
 * Transaction: uuid: String, sum: Long, account: Account
 * Both classes have getters for all fields with the corresponding names (getNumber(), getSum(), getAccount() and so on).
 * Write a collector that calculates the total sum of transactions (long type, not integer)
 * by each account (i.e. by account number). The collector will be applied to a stream of transactions.
 * <p>
 * Classes Transaction and Account will be available during testing. You can create your own classes for local testing.
 * <p>
 * Important. You should write only the collector in any valid formats but without ; on the end.
 * It will be passed as an argument to the collect() method of a stream as shown below.
 * <p>
 * List<Transaction> transactions = ...
 * Map<String, Long> totalSumOfTransByEachAccount =
 * transactions.stream()
 * .collect(...your_collector_will_be_passed_here...);
 * <p>
 * Examples of the valid solution formats: Collectors.reducing(...) or reducing(...)
 */
public class Solution {
    private static List<Transaction> transactions;

    static {
        Account firstAcc = new Account("111", 10_000L);
        Account secondAcc = new Account("222", 10_000L);

        Transaction trans1 = new Transaction("774cedda-9cde-4f53-8bc2-5b4d4859772a", 1_000L, firstAcc);
        Transaction trans2 = new Transaction("337868a7-f469-43c0-9042-3422ce37ee26a", 2_000L, firstAcc);
        Transaction trans3 = new Transaction("f8047f86-89e7-4226-8b75-74c55a4d7e31", 3_000L, secondAcc);
        Transaction trans4 = new Transaction("fgdf7f86-89e7-4226-8b75-74c55a4d7e32", 4_000L, secondAcc);

        transactions = Arrays.asList(trans1, trans2, trans3, trans4);
    }

    public static void main(String[] args) {
        Map<String, Long> totalSumOfTransByEachAccount = transactions.stream()
                .collect(Collectors.groupingBy(tr -> tr.getAccount().getNumber(),
                        summingLong(Transaction::getSum)));

        totalSumOfTransByEachAccount.forEach((k, v) -> System.out.println(k + " : " + v));

    }
}
