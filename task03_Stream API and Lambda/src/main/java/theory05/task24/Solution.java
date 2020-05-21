package theory05.task24;

import java.lang.reflect.GenericArrayType;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * You have two classes:
 * <p>
 * Transaction: uuid: String,  state: State (CANCELED, FINISHED, PROCESSING), sum: Long, created: Date
 * Account: number: String, balance: Long, transactions: List<Transaction>
 * Both classes have getters for all fields with the corresponding names (getState(), getSum(), getTransactions() and so on).
 * Write a method using Stream API that calculates the total sum of canceled transactions for
 * all non-empty accounts (balance > 0). Perhaps, flatMap method can help you to implement it.
 * <p>
 * Classes Transaction, Account and enum State will be available during testing.
 * You can define your own classes for local testing.
 * <p>
 * Examples: there are 2 accounts (in JSON notation) below. The result is 10 000.
 */

public class Solution {
    private static List<Account> accounts = new ArrayList<>();

    static {
        Transaction trans1 = new Transaction("774cedda-9cde-4f53-8bc2-5b4d4859772a", State.CANCELED, 1_000L, new Date());
        Transaction trans2 = new Transaction("337868a7-f469-43c0-9042-3422ce37ee26a", State.FINISHED, 8_000L, new Date());
        Transaction trans3 = new Transaction("f8047f86-89e7-4226-8b75-74c55a4d7e31", State.CANCELED, 10_000L, new Date());

        Account first = new Account("1001", 0L, Arrays.asList(trans1));
        Account second = new Account("1002", 8_000L, Arrays.asList(trans2, trans3));

        accounts.add(first);
        accounts.add(second);
    }

    /**
     * Calculates the general sum of canceled transactions for all non empty accounts in the list
     */
    public static long calcSumOfCanceledTransOnNonEmptyAccounts(List<Account> accounts) {
        // write your code here
        return accounts.stream()
                .filter(acc -> acc.getBalance() > 0)
                .flatMap(acc -> acc.getTransactions().stream())
                .filter(trans -> trans.getState() == State.CANCELED)
                .mapToLong(Transaction::getSum)
                .sum();
    }

    public static void main(String[] args) {
        System.out.println(calcSumOfCanceledTransOnNonEmptyAccounts(accounts));
    }
}

