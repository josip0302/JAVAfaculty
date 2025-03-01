package hr.fer.oprpp1.hw08.jnotepadpp;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.nio.file.Files.readAllBytes;

public class DefaultSingleDocumentModel implements SingleDocumentModel{
    JTextArea area;
    Path path;

    List<SingleDocumentListener> list;
    boolean isModified;
    public DefaultSingleDocumentModel(Path path,List<String> text) {
        this.path = path;
        area=new JTextArea();
        for(String s:text){
            area.append(s);
        }
        this.isModified=false;
        list=new ArrayList<>();
        area.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                for(SingleDocumentListener listener:list){
                    listener.documentModifyStatusUpdated(DefaultSingleDocumentModel.this);
                }
            }
        });
        area.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                setModified(true);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
               setModified(false);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                setModified(!isModified());
            }
        });



    }


    @Override
    public JTextArea getTextComponent() {
        return area;
    }

    @Override
    public Path getFilePath() {
        return path;
    }

    @Override
    public void setFilePath(Path path) {
        if(path==null) {
            throw new NullPointerException();
        }
       this.path=path;

    }

    @Override
    public boolean isModified() {
        return isModified;
    }

    @Override
    public void setModified(boolean modified) {
        isModified=modified;
    }

    @Override
    public void addSingleDocumentListener(SingleDocumentListener l) {
           list.add(l);
    }

    @Override
    public void removeSingleDocumentListener(SingleDocumentListener l) {
        list.remove(l);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultSingleDocumentModel that = (DefaultSingleDocumentModel) o;
        return Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path);
    }

    public void useList(){
        for (SingleDocumentListener listener:list){
            listener.documentFilePathUpdated(this);
        }
    }
}
