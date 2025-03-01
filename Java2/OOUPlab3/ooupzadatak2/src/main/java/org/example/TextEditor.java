package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.jar.JarEntry;

public class TextEditor extends JFrame implements CursorObserver,TextObserver {
    TextEditorModel model;
JTextArea area=new JTextArea();
JMenuBar bar=new JMenuBar();
    JLabel statusLabel;
    JPanel statusPanel;
    public TextEditor(TextEditorModel model) throws HeadlessException {
        this.model = model;
        model.addObserver(this);

        this.setSize(500,500);
        this.setLayout(new BorderLayout());
        for (Iterator<String> it = model.allLines(); it.hasNext(); ) {
            String s = it.next();
            area.append(s+"\n");
        }
        JMenu file=new JMenu("File");
        file.add(new JMenuItem("Open"));
        file.add(new JMenuItem("Save"));
        file.add(new JMenuItem("Exit"));
        JMenu edit=new JMenu("Edit");
        edit.add(new JMenuItem("Undo"));
        edit.add(new JMenuItem("Redo"));
        edit.add(new JMenuItem("Cut"));
        edit.add(new JMenuItem("Copy"));
        edit.add(new JMenuItem("Paste"));
        edit.add(new JMenuItem("Paste and Take"));
        edit.add(new JMenuItem("Delete selection"));
        edit.add(new JMenuItem("Clear document"));
        JMenu move=new JMenu("Move");
        move.add(new JMenuItem("Cursor to document start"));
        move.add(new JMenuItem("Cursor to document end"));
        bar.add(file);
        bar.add(edit);
        bar.add(move);
        this.add(bar,BorderLayout.NORTH);

        area.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    System.out.println("oce li");
                    model.moveCursorLeft();
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    model.moveCursorRight();
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    model.moveCursorUp();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    model.moveCursorDown();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    System.out.println("oce li");
                    model.moveCursorLeft();
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    model.moveCursorRight();
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    model.moveCursorUp();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    model.moveCursorDown();
                }
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    model.deleteAfter();
                }
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    model.deleteBefore();
                }
                if(e.getKeyCode()==KeyEvent.VK_ALPHANUMERIC ){
                    model.insert(e.getKeyChar());
                }
                if(e.getKeyCode()==KeyEvent.VK_ENTER ){
                    model.brejk();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
      statusPanel = new JPanel();

        this.add(statusPanel, BorderLayout.SOUTH);
        statusPanel.setPreferredSize(new Dimension(this.getWidth(), 16));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
        statusLabel = new JLabel("x:"+0+" , y:"+ 0);
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusPanel.add(statusLabel);
        this.add(area,BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    public void updateCursorLocation(Location loc) {
        statusLabel.setText("x:"+loc.x+" , y:"+ loc.y+" ,  lines:"+model.lines.size());

    }

    @Override
    public void updateText() {
        area.removeAll();
        for (Iterator<String> it = model.allLines(); it.hasNext(); ) {
            String s = it.next();
            area.append(s+"\n");
        }
        statusLabel.setText("x:"+model.cursorLocation.x+" , y:"+ model.cursorLocation.y+" ,  lines:"+model.lines.size());
    }
}
