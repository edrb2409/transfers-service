package io.edrb.transferservice.account.model;

import java.math.BigDecimal;
import java.util.StringJoiner;

public class Account {

    private String id;

    private String customerId;

    private BigDecimal balance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Account.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("customerId='" + customerId + "'")
                .add("balance=" + balance)
                .toString();
    }
}
