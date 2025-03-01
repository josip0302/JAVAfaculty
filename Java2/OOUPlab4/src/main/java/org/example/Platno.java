package org.example;

import javax.swing.*;
import java.awt.*;

public class Platno extends JComponent {
    DocumentModel model;

    public Platno(DocumentModel model) {
        this.model=model;
    }

    public void paintComponent(Graphics g) {
        System.out.println("zovi");
        Graphics2D g2d = (Graphics2D)g;
        Renderer r = new G2DRendererImpl(g2d);
        int x=0;
        for (Object o:model.list()){
            x++;
            AbstractGraphicalObject a= (AbstractGraphicalObject) o;
            a.render(r);
            if(a.isSelected()){
                System.out.println(1);
                Rectangle b=a.getBoundingBox();
                System.out.println(b.getX());
                g2d.setColor(Color.BLUE);
                g2d.drawRect(b.getX(),b.getY(),b.getWidth(),b.getHeight());
            }

        }

    }
}
