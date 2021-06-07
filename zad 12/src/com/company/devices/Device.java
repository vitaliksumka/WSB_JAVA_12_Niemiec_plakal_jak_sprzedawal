package com.company.devices;

import com.company.Salleable;

public abstract class Device implements Salleable {
    final String producer;
    final String model;
    public Integer yearOfProduction;
    public Double value;

    protected Device(String producer, String model, Double value) {
        this.producer = producer;
        this.model = model;
        this.value = value;
    }

    public String toString() {
        return "Producer: " + producer + " Model: " + model + " Year of Production:" + yearOfProduction;
    }

    public abstract void turnOn();
}
