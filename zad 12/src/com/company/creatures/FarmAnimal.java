package com.company.creatures;

public class FarmAnimal extends Animal implements Edbile {
    private boolean eaten = false;

    public FarmAnimal(String species) {
        super(species);
    }

    @Override
    public void beEaten() {
        if(!eaten){
            eaten = true;
            System.out.println("Zjedzono: " + this.species);
        }else{
            System.out.println("Nie ma już co jeść, zostały same kości!");
        }
    }
}
