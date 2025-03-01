package hr.fer.zemris.java.gui.calc;

import hr.fer.zemris.java.gui.calc.model.CalcModel;
import hr.fer.zemris.java.gui.calc.model.CalcValueListener;
import hr.fer.zemris.java.gui.layouts.CalcLayout;
import hr.fer.zemris.java.gui.layouts.RCPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.function.DoubleBinaryOperator;

public class Calculator extends JFrame {
    JButton[] buttonsNum;
    JButton[] binaryOp;
    JLabel labela;
    CalcModelImpl impl;
    JCheckBox box;
    public Calculator() throws HeadlessException {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        impl=new CalcModelImpl();
        initGUI();
        pack();


    }

    private void initGUI() {
        buttonsNum=new JButton[10];
        labela=new JLabel();
        CalcValueListener listener=new CalcValueListener() {
            @Override
            public void valueChanged(CalcModel model) {
                labela.setVisible(true);
                labela.setText(model.toString());
            }
        };
        impl.addCalcValueListener(listener);
        labela.setBackground(Color.YELLOW);

        Container cp = getContentPane();
        cp.setLayout(new CalcLayout(3));
        cp.add(labela, new RCPosition(1,1));
        addButtons(cp);
        addBinaryOperators(cp);
        addDotAndEquals(cp);

        addOther(cp);
        addInv(cp);
        addFunction(cp);
    }

    private void addInv(Container cp) {
         box=new JCheckBox("inv");
        ActionListener action2 = a -> {
           addFunction(cp);
            this.invalidate();
            this.validate();
            this.repaint();
        };
        cp.add(box,new RCPosition(5,7));
    }



    private void addOther(Container cp) {
        JButton clr=new JButton("clr");
        ActionListener action = a -> {
            impl.clear();
        };
        clr.addActionListener(action);
        cp.add(clr,new RCPosition(1,7));
        JButton reset=new JButton("reset");
        ActionListener action2 = a -> {
            impl.clearAll();
        };
        reset.addActionListener(action);
        cp.add(reset,new RCPosition(2,7));
        JButton push=new JButton("push");
        JButton pop=new JButton("pop");
        cp.add(push,new RCPosition(3,7));
        cp.add(pop,new RCPosition(4,7));
    }

    private void addFunction(Container cp) {
        JButton[] f=new JButton[8];
        String[] s={"1/x","log","ln","x^n","sin","cos","tan","ctg"};
        if(box.isSelected()){
            s= new String[]{"1/x", "10^x","e^x","x^(1/n)","arcsin","arccos","arctan","arcctg"};
        }
        int brojac=0;
        for(int i = 0;i<2;i++){
            for(int j=0;j<4;j++){
                f[brojac]=new JButton(s[brojac]);
                cp.add(f[brojac], new RCPosition(j+2,i+1));
                brojac++;
            }
        }
    }

    private void addDotAndEquals(Container cp) {
        JButton tocka=new JButton(".");
        ActionListener action = a -> {
            impl.insertDecimalPoint();
        };
        tocka.addActionListener(action);
        cp.add(tocka,new RCPosition(5,5));
        JButton jednako=new JButton("=");
        ActionListener action2 = a -> {
            impl.setValue(impl.getPendingBinaryOperation().applyAsDouble(impl.getActiveOperand(), impl.getValue()));
        };
        jednako.addActionListener(action2);
        cp.add(jednako,new RCPosition(1,6));
        JButton plusminus=new JButton("x/-");
        cp.add(plusminus,new RCPosition(5,4));

    }

    private void addBinaryOperators(Container cp) {



        binaryOp=new JButton[4];
        String[] op={"/","*","-","+"};
        int brojac=0;
        for (String S:op){
            ActionListener action = a -> {
                JButton b = (JButton)a.getSource();
                if(S.equals("+")){
                impl.setPendingBinaryOperation(Double::sum);
                } else if (S.equals("-")) {
                    impl.setPendingBinaryOperation(new DoubleBinaryOperator() {
                        @Override
                        public double applyAsDouble(double left, double right) {
                            return left-right;
                        }
                    });
                }else if (S.equals("/")) {
                    impl.setPendingBinaryOperation(new DoubleBinaryOperator() {
                        @Override
                        public double applyAsDouble(double left, double right) {
                            return left/right;
                        }
                    });
            }else if (S.equals("*")) {
                    impl.setPendingBinaryOperation(new DoubleBinaryOperator() {
                        @Override
                        public double applyAsDouble(double left, double right) {
                            return left * right;
                        }
                    });

                }
            };

            binaryOp[brojac]=new JButton(op[brojac]);
            binaryOp[brojac].addActionListener(action);
            cp.add(binaryOp[brojac], new RCPosition(brojac+2,6));
            brojac++;
        }

    }

    private void addButtons( Container cp) {

        ActionListener action = a -> {
            JButton b = (JButton)a.getSource();
            impl.insertDigit(Integer.valueOf(b.getText()));

        };
        buttonsNum[0]=new JButton("0");
        buttonsNum[0].addActionListener(action);
        cp.add(buttonsNum[0], new RCPosition(5,3));
        int brojac=1;
        for(int i =0;i<3;i++){
            for(int j=0;j<3;j++){
                buttonsNum[brojac]=new JButton(Integer.toString(brojac));
                buttonsNum[brojac].addActionListener(action);
                cp.add(buttonsNum[brojac], new RCPosition(i+2,j+3));
                brojac++;
            }
        }
    }

    private JLabel l(String text) {
        JLabel l = new JLabel(text);
        l.setBackground(Color.YELLOW);
        l.setOpaque(true);
        return l;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{new Calculator().setVisible(true);});
    }
}
