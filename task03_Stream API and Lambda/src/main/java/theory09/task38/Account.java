package theory09.task38;

import java.util.UUID;

public class Account {
    private UUID guid;
    private long balance;

    public Account(UUID guid, long balance) {
        this.guid = guid;
        this.balance = balance;
    }

    public UUID getGuid() {
        return guid;
    }

    public long getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "guid=" + guid +
                ", balance=" + balance +
                '}';
    }
}
