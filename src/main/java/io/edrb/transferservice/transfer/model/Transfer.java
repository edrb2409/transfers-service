package io.edrb.transferservice.transfer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.StringJoiner;

public class Transfer {

    private String sourceCustomerId;

    private String sourceAccountId;

    private String destinationAccount;

    private BigDecimal amount;

    @JsonIgnore
    private final LocalDateTime timestamp;

    public Transfer() {
        timestamp = LocalDateTime.now();
    }

    public String getSourceCustomerId() {
        return sourceCustomerId;
    }

    public void setSourceCustomerId(String sourceCustomerId) {
        this.sourceCustomerId = sourceCustomerId;
    }

    public String getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(String sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public String getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(String destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Transfer.class.getSimpleName() + "[", "]")
                .add("sourceCustomerId='" + sourceCustomerId + "'")
                .add("sourceAccountId='" + sourceAccountId + "'")
                .add("destinationAccount='" + destinationAccount + "'")
                .add("amount=" + amount)
                .toString();
    }
}
