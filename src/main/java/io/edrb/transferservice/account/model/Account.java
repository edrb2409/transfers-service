package io.edrb.transferservice.account.model;

import io.edrb.transferservice.account.exception.FundsNotAvailableException;

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

    public void withdrawal(BigDecimal amount) {
        if(this.balance.compareTo(amount) < 0)
            throw new FundsNotAvailableException();

        this.balance = this.balance.subtract(amount);
    }

    public void deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
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
