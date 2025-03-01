package hr.fer.zemris.java.gui.prim;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PrimDemo extends JFrame {

    private static final long serialVersionUID = 1L;

    public PrimDemo() {
        setLocation(20, 50);
        setSize(300, 200);
        setTitle("Moj prvi prozor!");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        initGUI();
    }

    static class PrimListModel implements ListModel<Integer> {


        private List<Integer> lista1 ;
        private List<ListDataListener> lista2 ;
        public PrimListModel() {
            this.lista1 =  new ArrayList<>();
            lista1.add(1);
            this.lista2 = new ArrayList<>();
        }

        @Override
        public void addListDataListener(ListDataListener l) {
            lista2.add(l);
        }
        @Override
        public void removeListDataListener(ListDataListener l) {
            lista2.remove(l);
        }

        @Override
        public int getSize() {
            return lista1.size();
        }
        @Override
        public Integer getElementAt(int index) {
            return lista1.get(index);
        }
        public void next(){
            int x = lista1.size();
            int a=lista1.get(getSize()-1);
            boolean v=true;
            while (v){
                a++;
                v=false;
                for (int i = 2;i<a;i++){
                    if(a%i==0){
                        v=true;
                    }
                }
            }
                lista1.add(a);

            ListDataEvent event = new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, x, x);
            for(ListDataListener l : lista2) {
                l.intervalAdded(event);
            }
        }


    }


    private void initGUI() {
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        PrimListModel model = new PrimListModel();

        JList<Integer> list1 = new JList<>(model);
        JList<Integer> list2 = new JList<>(model);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 0));

        JButton next = new JButton("sljedeći");

        next.addActionListener(e -> {
            model.next();
        });
        bottomPanel.add(next);


        JPanel central = new JPanel(new GridLayout(1, 0));
        central.add(new JScrollPane(list1));
        central.add(new JScrollPane(list2));

        cp.add(central, BorderLayout.CENTER);
        cp.add(bottomPanel, BorderLayout.PAGE_END);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new PrimDemo();
            frame.pack();
            frame.setVisible(true);
        });
    }
}

