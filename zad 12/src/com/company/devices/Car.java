package com.company.devices;

import com.company.Human;
import com.company.Transaction;

import java.sql.Timestamp;
import java.util.ArrayList;

public abstract class Car extends Device {
    private String plates;
    private final ArrayList<Transaction> transactionsHistory = new ArrayList<>();

    public Car(String producer, String model) {
        super(producer, model, 10000.0);
    }

    public String getPlates() {
        return plates;
    }

    public void setPlates(String plates) {
        this.plates = plates;
    }

    public String toString() {
        return producer + " " + model + " " + plates;
    }

    @Override
    public void turnOn() {
        System.out.println("Samochód " + this.producer + " " + this.model + " uruchomiony. Możesz jechać!");
    }

    public String getProducer() {
        return producer;
    }

    public String getModel() {
        return model;
    }

    public void buyAsNew(Human buyer) throws Exception {
        if (buyer.couldBuy(this, value) && buyer.getNumberParkingFreeSpace() != null) {
            buyer.setCar(buyer.getNumberParkingFreeSpace(), this);
            buyer.cash -= value;
            addNewTransaction(null, buyer, value);
            System.out.println("Tranzakcja udana.");
        } else {
            System.out.println("Tranzakcja nie powiodła się!");
        }
    }

    @Override
    public void sell(Human seller, Human buyer, Double price) throws Exception {
        if (buyer.couldBuy(this, price) && seller.hasDevice(this) && buyer.getNumberParkingFreeSpace() != null) {
            buyer.setCar(buyer.getNumberParkingFreeSpace(), this);
            seller.removeCar(this);
            buyer.cash -= price;
            seller.cash += price;
            addNewTransaction(seller, buyer, price);
            System.out.println("Tranzakcja udana.");
        } else {
            System.out.println("Tranzakcja nie powiodła się!");
        }
    }

    public abstract void refuel();

    public void addNewTransaction(Human seller, Human buyer, Double transactionValue) {
        if (transactionsHistory.isEmpty()) {
            transactionsHistory.add(new Transaction(null, buyer, transactionValue, new Timestamp(System.currentTimeMillis())));
        } else {
            transactionsHistory.add(new Transaction(seller, buyer, transactionValue, new Timestamp(System.currentTimeMillis())));
        }
    }

    public boolean wasOwner(Human likelyOwner) {
        for (Transaction transaction : transactionsHistory) {
            if (transaction.buyer == likelyOwner) {
                return true;
            }
        }
        return false;
    }

    public boolean isExistTransactionBetween(Human seller, Human buyer) {
        for (Transaction transaction : transactionsHistory) {
            if (transaction.seller == seller && transaction.buyer == buyer) {
                return true;
            }
        }
        return false;
    }

    public Integer getNumberOfTransactions() {
        return transactionsHistory.size();
    }

    public Transaction getLastTransaction() {
        return transactionsHistory.get(transactionsHistory.size() - 1);
    }
}
