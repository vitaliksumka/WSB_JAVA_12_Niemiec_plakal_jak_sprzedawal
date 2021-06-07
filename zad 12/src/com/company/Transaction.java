package com.company;

import java.sql.Timestamp;

public class Transaction {
    final public Human seller;
    final public Human buyer;
    final Double transactionValue;
    final Timestamp transactionTimestamp;

    public Transaction(Human seller, Human buyer, Double transactionValue, Timestamp transactionTimestamp) {
        this.seller = seller;
        this.buyer = buyer;
        this.transactionValue = transactionValue;
        this.transactionTimestamp = transactionTimestamp;
    }
}
