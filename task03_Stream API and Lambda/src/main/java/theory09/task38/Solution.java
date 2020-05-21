package theory09.task38;

import java.util.*;
import java.util.function.Supplier;

/**
 * task 01
 * You need to write a method findUserByLogin(String login) that returns an optional value of type Optional<User>.
 * If the user exists in the users set you need to return non-empty optional wrapping the user inside,
 * otherwise returned optional should be empty.
 * <p>
 * task 02
 * Using the method you've written for finding an user by their login,
 * write a new method printBalanceIfNotEmpty(String userLogin) that prints an account balance for an existing
 * user if `balance > 0`. In this case, the result format should print the string:
 * <p>
 * login: balance
 * <p>
 * If the user is not found, account is null or the balance = 0, then the method should not print anything.
 */

public class Solution {
    private static Set<User> users;
    private static Supplier<? extends User> mockUser = () -> new User("No such User", null);

    static {
        Account acc1 = new Account(UUID.randomUUID(), 10_000L);
        Account acc2 = new Account(UUID.randomUUID(), 20_000L);
        Account acc3 = new Account(UUID.randomUUID(), 30_000L);
        Account acc4 = new Account(UUID.randomUUID(), -40_000L);

        User user1 = new User("firstUser@login", acc1);
        User user2 = new User("secongUser@login", acc2);
        User user3 = new User("thirdUser@login", null);
        User user4 = new User("forthUser@login", acc4);

        users = new HashSet<>(Arrays.asList(user1, user2, user3, user4));
    }

    public static Optional<User> findUserByLogin(String login) {
        return users.stream()
                .filter(user -> user.getLogin().equalsIgnoreCase(login))
                .findAny();
    }

    public static void printBalanceIfNotEmpty(String userLogin) {
        findUserByLogin(userLogin)
                .map(User::getAccount)
                .map(Account::getBalance)
                .filter(balance -> balance > 0)
                .ifPresent(balance -> System.out.println(userLogin + ": " + balance));


    }

    public static void main(String[] args) {
        printBalanceIfNotEmpty("firstUser@login");
        printBalanceIfNotEmpty("2User@login");
        printBalanceIfNotEmpty("thirdUser@login");
        printBalanceIfNotEmpty("forthUser@login");
    }

}
