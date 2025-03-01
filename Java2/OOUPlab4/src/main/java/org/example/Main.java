package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List objects = new ArrayList<>();

        objects.add(new LineSegment(new Point(100,110),new Point(150,200)));
        objects.add(new Oval());

        GUI gui = new GUI(objects);
        gui.setVisible(true);
    }
}