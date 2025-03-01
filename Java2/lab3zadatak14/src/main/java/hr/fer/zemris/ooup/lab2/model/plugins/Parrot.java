package hr.fer.zemris.ooup.lab2.model.plugins;

import hr.fer.zemris.ooup.lab2.model.Animal;

public class Parrot extends Animal {
    private String animalName;

    public Parrot(String animalName) {
        this.animalName = animalName;
    }

    @Override
    public String name() {
        return animalName;
    }

    @Override
    public String greet() {
        return "bok bok";
    }

    @Override
    public String menu() {
        return "voće";
    }
}
