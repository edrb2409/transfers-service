package io.edrb.transferservice.transfer.model;

import java.time.LocalDateTime;
import java.util.StringJoiner;

public class TransferLog {

    private String id;

    private LocalDateTime timestamp;

    private Transfer transfer;

    private TransferState state;

    public TransferLog() {
        this.timestamp = LocalDateTime.now();
    }

    public TransferLog(Transfer transfer) {
        this();
        this.transfer = transfer;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Transfer getTransfer() {
        return transfer;
    }

    public TransferState getState() {
        return state;
    }

    public void setState(TransferState state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setTransfer(Transfer transfer) {
        this.transfer = transfer;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TransferLog.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("timestamp=" + timestamp)
                .add("transfer=" + transfer)
                .add("state=" + state)
                .toString();
    }
}
