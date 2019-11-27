package com.applitools.hackathon.domain;

import java.util.Objects;

public class TransactionsTable {

    private String status;
    private String date;
    private String description;
    private String category;
    private String amount;

    public TransactionsTable(String status, String date, String description, String category, String amount) {
        this.status = status;
        this.date = date;
        this.description = description;
        this.category = category;
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionsTable that = (TransactionsTable) o;
        return Objects.equals(status, that.status) &&
                Objects.equals(date, that.date) &&
                Objects.equals(description, that.description) &&
                Objects.equals(category, that.category) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, date, description, category, amount);
    }

    @Override
    public String toString() {
        return "'" + description + '\'' +
                ", '" + category + '\'';
    }
}
