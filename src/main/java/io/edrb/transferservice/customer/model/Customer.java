package io.edrb.transferservice.customer.model;

import java.util.StringJoiner;

public class Customer {

    private String id;

    private String completeName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompleteName() {
        return completeName;
    }

    public void setCompleteName(String completeName) {
        this.completeName = completeName;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Customer.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("completeName='" + completeName + "'")
                .toString();
    }
}
