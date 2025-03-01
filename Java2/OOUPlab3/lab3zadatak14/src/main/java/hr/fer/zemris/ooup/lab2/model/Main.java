package hr.fer.zemris.ooup.lab2.model;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i+=2) {
            Animal animal=AnimalFactory.newInstance(args[i],args[i+1]);
            animal.animalPrintGreeting();
            animal.animalPrintMenu();
        }
    }
}
