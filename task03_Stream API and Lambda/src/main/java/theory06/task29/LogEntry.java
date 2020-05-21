package theory06.task29;

import java.util.Date;

public class LogEntry {
    private Date create;
    private String login;
    private String url;

    public LogEntry(Date create, String login, String url) {
        this.create = create;
        this.login = login;
        this.url = url;
    }

    public Date getCreate() {
        return create;
    }

    public String getLogin() {
        return login;
    }

    public String getUrl() {
        return url;
    }
}
