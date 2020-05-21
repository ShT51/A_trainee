package theory05.task24;

import java.util.Date;

class Transaction {
    private String uuid;
    private State state;
    private Long sum;
    private Date created;

    public Transaction(String uuid, State state, Long sum, Date created) {
        this.uuid = uuid;
        this.state = state;
        this.sum = sum;
        this.created = created;
    }

    public String getUuid() {
        return uuid;
    }

    public State getState() {
        return state;
    }

    public Long getSum() {
        return sum;
    }

    public Date getCreated() {
        return created;
    }
}
