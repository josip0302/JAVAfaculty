package hr.fer.zemris.ooup.lab2.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class AnimalFactory {
    public static Animal newInstance(String animalKind, String name) {
        Class<Animal> clazz = null;
        try {
            clazz = (Class<Animal>)Class.forName("hr.fer.zemris.ooup.lab2.model.plugins."+animalKind);
            Constructor<?> ctr = clazz.getConstructor(String.class);
            Animal animal = (Animal)ctr.newInstance(name);
            return animal;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }


    }
}
