package theory06.task28;

public class Transaction {
    private String uuid;
    private Long sum;
    private Account account;

    public Transaction(String uuid, Long sum, Account account) {
        this.uuid = uuid;
        this.sum = sum;
        this.account = account;
    }

    public String getUuid() {
        return uuid;
    }

    public Long getSum() {
        return sum;
    }

    public Account getAccount() {
        return account;
    }
}
