package com.company.creatures;

import com.company.Human;
import com.company.Salleable;

import java.io.File;

public abstract class Animal implements Salleable, Feedable {
    final static Double DEFAULT_WEIGHT_DOG = 4.0;
    final static Double DEFAULT_WEIGHT_LION = 190.0;
    final static Double DEFAULT_WEIGHT_MOUSE = 0.01;

    public String name;
    public File pic;
    final String species;
    private Double weight;

    public Animal(String species) {
        this.species = species;

        switch (this.species) {
            case "Mouse":
                weight = DEFAULT_WEIGHT_MOUSE;
                break;
            case "Dog":
                weight = DEFAULT_WEIGHT_DOG;
                break;
            case "Lion":
                weight = DEFAULT_WEIGHT_LION;
            default:
                weight = 100.0;
        }
    }

    boolean isDead() {
        switch (this.species) {
            case "Mouse":
                if (weight <= DEFAULT_WEIGHT_MOUSE * .3) {
                    return true;
                }
                break;
            case "Dog":
                if (weight <= DEFAULT_WEIGHT_DOG * .3) {
                    return true;
                }
                break;
            case "Lion":
                if (weight <= DEFAULT_WEIGHT_LION * .3) {
                    return true;
                }
                break;
            default:
                if (weight <= 0) {
                    return true;
                }
                break;
        }
        return false;
    }

    public void feed() {
        feed(1.0);
    }

    @Override
    public void feed(Double foodWeight) {
        if (isDead()) {
            System.out.println("too late, sorry");
        } else {
            weight += foodWeight;
            System.out.println("thx, my weight is now " + weight);
        }
    }

    public void takeForAWalk() {
        if (isDead()) {
            System.out.println("Too late...");
        } else if (weight <= 3) {
            weight--;
            if (weight <= 0) {
                System.out.println("Thx, nice walk, but i'm dying...");
            } else {
                System.out.println("Thx, nice walk, but i'm hungry.    Weight: " + weight);
            }
        } else {
            weight--;
            System.out.println("Thx, nice walk.    Weight: " + weight);
        }
    }

    public String getSpecies() {
        return species;
    }

    public String toString() {
        return species + " " + name;
    }

    @Override
    public void sell(Human seller, Human buyer, Double price) throws Exception {
        if (buyer.couldBuy(this, price) && seller.hasAnimal(this)) {
            buyer.pet = this;
            seller.pet = null;
            buyer.cash -= price;
            seller.cash += price;
            System.out.println("Tranzakcja udana.");
        } else {
            System.out.println("Tranzakcja nie powiodła się!");
        }
    }
}
