package com.company.devices;

public class LpgCar extends Car {
    public LpgCar(String producer, String model) {
        super(producer, model);
    }

    @Override
    public void refuel() {
        System.out.println("Nie ma to jak gaz! Teraz masz go pod dostatkiem!");
    }

    public String toString() {
        return "LPG car: " + this.producer + " " + this.model + " " + this.getPlates();
    }
}
