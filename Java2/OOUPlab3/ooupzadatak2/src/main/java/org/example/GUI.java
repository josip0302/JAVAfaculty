package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;

public class GUI extends JFrame {

JPanel panel=new JPanel();
    public GUI() throws HeadlessException {
        this.setSize(500,500);
        panel.add(new JLabel("Labos iz ovoga mi je za 4 ipo sata"));
        panel.add(new JLabel("Labos iz ovoga mi je za 4 ipo sata"));
        this.getContentPane().add(panel);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                   JFrame f= (JFrame) e.getSource();
                   f.dispose();
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        this.setVisible(true);
    }
    public void paint(Graphics g) {
        super.paint(g);  // fixes the immediate problem.
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(1));
        Line2D lin = new Line2D.Float(20, 40, 850, 40);

        Line2D lin2 = new Line2D.Float(40, 20, 40, 850);
        g2.draw(lin);
        g2.draw(lin2);
    }

}
