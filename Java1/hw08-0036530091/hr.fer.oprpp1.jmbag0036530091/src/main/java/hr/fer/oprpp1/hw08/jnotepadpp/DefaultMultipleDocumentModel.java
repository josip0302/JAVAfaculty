package hr.fer.oprpp1.hw08.jnotepadpp;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DefaultMultipleDocumentModel extends JTabbedPane implements MultipleDocumentModel{

    List<SingleDocumentModel> list;
    SingleDocumentModel current;
    List<MultipleDocumentListener> listMDL;
    ImageIcon red,green;
    public DefaultMultipleDocumentModel() {

        this.list = new ArrayList<>();
        this.listMDL=new ArrayList<>();
       this.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
                int selectedIndex = tabbedPane.getSelectedIndex();
                current= getDocument(selectedIndex);
            }
        });

     /*   try {
            red=new ImageIcon(getClass().getResource("/resources/crvena.png"));

           // green=new ImageIcon(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/
    }



    @Override
    public JComponent getVisualComponent() {
        return this;
    }

    @Override
    public SingleDocumentModel createNewDocument() {
        current=new DefaultSingleDocumentModel(null,new ArrayList<>());
        this.addTab("unkown",new JScrollPane(current.getTextComponent()));
        return current;
    }

    @Override
    public SingleDocumentModel getCurrentDocument() {

        return current;
    }

    @Override
    public SingleDocumentModel loadDocument(Path path) {
        if(path==null)throw new NullPointerException();
        SingleDocumentModel model=  this.findForPath(path);
        if(model!=null){
        //    current=model;
        }else {
            List<String> text;
            try {
                text= Files.readAllLines(path, StandardCharsets.UTF_8);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Pogreška prilikom čitanja datoteke "+path+".",
                        "Pogreška",JOptionPane.ERROR_MESSAGE);
                return null;
            }
            model=new DefaultSingleDocumentModel(path,text);
           setIconAt(getSelectedIndex(),current.isModified()?red:green);


           current=model;

        }

        JButton btnClose = new JButton("X");
        this.addTab(path.toString(),new JScrollPane(current.getTextComponent()));
        DefaultSingleDocumentModel model2=(DefaultSingleDocumentModel)current;
        //this.setIconAt(this.getSelectedIndex(),model2.getIcon());
        return model;
    }

    @Override
    public void saveDocument(SingleDocumentModel model, Path newPath) {
        if(findForPath(newPath)!=null){
            JOptionPane.showMessageDialog(
                    this,"File with path:"+newPath.toString()+" already is opened," +
                    "try to close this file before saving this document!","Upozorenje", JOptionPane.WARNING_MESSAGE);
            return;
        }else {
            byte[] podatci = model.getTextComponent().getText().getBytes(StandardCharsets.UTF_8);

            try {
                Files.write(newPath, podatci);
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(
                        this,
                        "Pogreška prilikom zapisivanja datoteke "+newPath.toFile().getAbsolutePath()+".\nPažnja: nije jasno u kojem je stanju datoteka na disku!",
                        "Pogreška",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(
                    this,
                    "Datoteka je snimljena.",
                    "Informacija",
                    JOptionPane.INFORMATION_MESSAGE);
          model.setFilePath(newPath);
          this.setTitleAt(indexOfComponent(getSelectedComponent()),newPath.toString());
        }
    }

    @Override
    public void closeDocument(SingleDocumentModel model) {
         this.remove((Component) model);
         list.remove(model);
    }

    @Override
    public void addMultipleDocumentListener(MultipleDocumentListener l) {
        this.listMDL.add(l);
    }

    @Override
    public void removeMultipleDocumentListener(MultipleDocumentListener l) {
        this.listMDL.remove(l);
    }

    @Override
    public int getNumberOfDocuments() {
        return list.size();
    }

    @Override
    public SingleDocumentModel getDocument(int index) {
        return list.get(index);
    }

    @Override
    public SingleDocumentModel findForPath(Path path) {
        if(path==null)throw new NullPointerException();
        for(SingleDocumentModel m:list){
            if(m.getFilePath().compareTo(path)==0){
                return m;
            }
        }
        return null;
    }

    @Override
    public int getIndexOfDocument(SingleDocumentModel doc) {
        this.indexOfComponent(doc.getTextComponent());
        return list.indexOf(doc);
    }

    @Override
    public Iterator<SingleDocumentModel> iterator() {
        return list.iterator();
    }
}
