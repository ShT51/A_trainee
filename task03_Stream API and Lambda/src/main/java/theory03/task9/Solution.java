package theory03.task9;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * You have a class Account (number: String, balance: Long, isLocked: boolean), the list accounts of type List<Account>
 * and the method filter(List<T> elems, Predicate<T> predicate) for filtering the given list of type T by the predicate.
 *
 * The class Account has the next methods: getNumber(), getBalance(), isLocked() for getting the values of the corresponding fields.
 *
 * Write a code for filtering the accounts list in two ways:
 *      get list with all non-empty accounts (balance > 0) and save it to the variable nonEmptyAccounts
 *      get all non-locked accounts with too much money (balance >= 100 000 000) and save it to the variable accountsWithTooMuchMoney
 *
 * The class Account, the list accounts (List<Account>) and the method filter(...) will be available during testing.
 */
public class Solution {
    private static List<Account> accounts = new ArrayList<>();

    private static void fillAccounts() {
        accounts.add(new Account("rich and unlock", 100_000_000L, false));
        accounts.add(new Account("rich and locked", 200_000_00L, true));
        accounts.add(new Account("poor and locked", -100L, true));
        accounts.add(new Account("poor and unlocked", -200L, true));
        accounts.add(new Account("average and unlocked", 10_000L, false));
        accounts.add(new Account("average and locked", 20_000L, true));
    }

    public static void main(String[] args) {
        AccountFilter<Account> accountFilter = (elem, predicate) -> elem.stream().filter(predicate).collect(Collectors.toList());

        Predicate<Account> nonEmpty = account -> account.getBalance() > 0;
        Predicate<Account> isBlocked = Account::isLocked;
        Predicate<Account> tooMuchMoney = account -> account.getBalance() >= 100_000_000L;

        fillAccounts();
        List<Account> nonEmptyAccounts = accountFilter.filter(accounts, nonEmpty);
        List<Account> accountsWithTooMuchMoney = accountFilter.filter(accounts, isBlocked.negate().and(tooMuchMoney));

        /*List<Account> nonEmptyAccounts = accountFilter.filter(accounts, account -> account.getBalance() > 0);
        List<Account> accountsWithTooMuchMoney = accountFilter.filter(accounts, account ->
                !account.isLocked()
                        && account.getBalance() >= 100_000_000L);*/

        nonEmptyAccounts.forEach(System.out::println);
        System.out.println("===============================");
        accountsWithTooMuchMoney.forEach(System.out::println);
    }
}

@FunctionalInterface
interface AccountFilter<T> {
    List<T> filter(List<T> elems, Predicate<T> predicate);
}