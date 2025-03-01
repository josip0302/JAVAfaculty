package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.cert.TrustAnchor;
import java.util.List;
import java.util.jar.JarEntry;

public class GUI extends JFrame {
    DocumentModel model;
    Platno platno;
    private State currentState=new IdleState();
    public GUI(List objects) {


        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setFocusable(true);
        model=new DocumentModel();
        platno=new Platno(model);
        platno.setFocusable(true);
        model.addDocumentModelListener(new DocumentModelListener() {
            @Override
            public void documentChange() {

                platno.repaint();

            }
        });
        JMenuBar bar=new JMenuBar();
        bar.setFocusable(false);
        for(Object o:objects){
            GraphicalObject g=(GraphicalObject)o;
           JButton b=new JButton(g.getShapeName());
           b.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   currentState=new AddShapeState(model,g);
               }
           });
            b.setFocusable(false);
           bar.add(b);
            model.addGraphicalObject(g);
        }

        JButton b=new JButton("Selektiraj");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentState=new SelectShapeState(model);
            }
        });
        b.setFocusable(false);
        bar.add(b);
        addListeners();
        this.setLayout(new BorderLayout());
        this.add(bar,BorderLayout.NORTH);

        this.add(platno,BorderLayout.CENTER);
        this.setSize(500,500);

    }

    private void addListeners() {
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentState.mouseDown(new Point(e.getPoint().x,e.getPoint().y),e.isShiftDown(),e.isControlDown());
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }


        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                currentState.mouseDragged(new Point(e.getPoint().x,e.getPoint().y));

            }
        });
        this.addKeyListener(new KeyAdapter() {


            @Override
            public void keyPressed(KeyEvent e) {
                if( e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    currentState=new IdleState();
                }
            }


        });
    }
}
