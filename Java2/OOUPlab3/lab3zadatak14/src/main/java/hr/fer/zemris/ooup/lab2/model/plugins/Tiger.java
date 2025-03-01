package hr.fer.zemris.ooup.lab2.model.plugins;

import hr.fer.zemris.ooup.lab2.model.Animal;

public class Tiger extends Animal {
    private String animalName;

    public Tiger(String animalName) {
        this.animalName = animalName;
    }

    @Override
    public String name() {
        return animalName;
    }

    @Override
    public String greet() {
        return "grr";
    }

    @Override
    public String menu() {
        return "Jelene";
    }
}
