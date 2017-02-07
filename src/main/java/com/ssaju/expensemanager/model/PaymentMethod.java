package com.ssaju.expensemanager.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentMethod {

    private String type;
    private String name;
    private String description;

    @JsonCreator
    public PaymentMethod(@JsonProperty("type") final String type,
                         @JsonProperty("name") final String name,
                         @JsonProperty("description") final String description) {
        this.type = type;
        this.name = name;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setType(String type) {
        this.type = type;
    }
}
