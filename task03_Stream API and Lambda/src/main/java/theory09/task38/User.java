package theory09.task38;

public class User {
    private String login;
    private Account account;

    public User(String login, Account account) {
        this.login = login;
        this.account = account;
    }

    public String getLogin() {
        return login;
    }

    public Account getAccount() {
        return account;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", account=" + account +
                '}';
    }
}
