package org.example;

import jdk.javadoc.doclet.Taglet;



import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TextEditorModel {
    List<String> lines;
   LocationRange selectionRange;
Location cursorLocation;
List<CursorObserver> observers=new ArrayList<>();
    List<TextObserver> textObservers=new ArrayList<>();
    public TextEditorModel(String text) {
        cursorLocation=new Location(0,0);
        lines =new ArrayList<>(List.of(text.split("\n")));

    }
    public void addObserver(CursorObserver o){
        observers.add(o);
    }
    public  void  removeObserver(CursorObserver o){
        observers.remove(o);
    }
    public void addTextObserver(TextObserver o){
        textObservers.add(o);
    }
    public  void  removeTextObserver(TextObserver o){
        textObservers.remove(o);
    }

    public void moveCursorLeft(){
      cursorLocation.x--;
        System.out.println(cursorLocation.x);
      notifyObservers();
    }

    private void notifyObservers() {
        for (CursorObserver o:observers){
            o.updateCursorLocation(cursorLocation);
        }
    }
    private void notifyTextObservers() {
        for (TextObserver o:textObservers){
           o.updateText();
        }
    }

    public void moveCursorRight(){
        cursorLocation.x++;
        notifyObservers();
    }
    public void moveCursorUp(){
         cursorLocation.y++;
        notifyObservers();
    }
    public void moveCursorDown(){
         cursorLocation.y--;
        notifyObservers();
    }
    public void print(){
        for (String s:lines){
            System.out.println(s);
        }
    }
    public Iterator<String> allLines(){
        return lines.iterator();
    }
    public  Iterator<String> linesRange(int index1, int index2){
        List<String>list=new ArrayList<>();
        for (int i = index1; i < index2; i++) {
            list.add(lines.get(i));
        }
        return list.iterator();
    }

    public void deleteBefore(){
       String s=lines.remove(-cursorLocation.y);
       s=s.substring(0,cursorLocation.x)+s.substring(cursorLocation.x+1);
       this.moveCursorLeft();
       lines.add(-cursorLocation.y, s);
       notifyTextObservers();
    }
    public void deleteAfter(){
        String s=lines.remove(-cursorLocation.y);
        s=s.substring(0,cursorLocation.x+1)+s.substring(cursorLocation.x+2);
        lines.add(-cursorLocation.y, s);
        notifyTextObservers();
    }

    public LocationRange getSelectionRange() {
        return selectionRange;
    }

    public void setSelectionRange(LocationRange selectionRange) {
        this.selectionRange = selectionRange;
    }

    public void insert(char c){
        String s=lines.remove(-cursorLocation.y);
        s=s.substring(0,cursorLocation.x)+c+s.substring(cursorLocation.x);
        cursorLocation.x+=1;
        lines.add(-cursorLocation.y, s);
        notifyTextObservers();
    }
    public void insert(String c){
        String s=lines.remove(-cursorLocation.y);
        s=s.substring(0,cursorLocation.x)+c+s.substring(cursorLocation.x);
        cursorLocation.x+=c.length();
        lines.add(-cursorLocation.y, s);
        notifyTextObservers();
    }

    public void brejk(){
        String s=lines.remove(-cursorLocation.y);

        lines.add(-cursorLocation.y, s.substring(0,cursorLocation.x));
        lines.add(-cursorLocation.y+1, s.substring(cursorLocation.x));
        cursorLocation.x=0;
        cursorLocation.y-=1;
        notifyTextObservers();
    }
}
