package hr.fer.zemris.java.gui.calc;

import hr.fer.zemris.java.gui.calc.model.CalcModel;
import hr.fer.zemris.java.gui.calc.model.CalcValueListener;
import hr.fer.zemris.java.gui.calc.model.CalculatorInputException;

import java.util.function.DoubleBinaryOperator;

public class CalcModelImpl implements CalcModel {

    private int digits;
    private boolean editable;
    private boolean negative;
    private  String znamenkaS;
    private double znamenkaD;
    private String frozen;
    private double activeOperand;
    private boolean isActiveOperandSet;
    private DoubleBinaryOperator pendingOperation;
    CalcValueListener listener;
    public CalcModelImpl() {
        this.editable = true;
        this.negative = false;
        this.znamenkaS = "";
        this.znamenkaD = 0;
        this.frozen = null;
       this.isActiveOperandSet=false;
        this.pendingOperation=null;
        this.digits=0;
    }

    @Override
    public void addCalcValueListener(CalcValueListener l) {
        listener=l;

    }

    @Override
    public void removeCalcValueListener(CalcValueListener l) {
        listener=null;

    }

    @Override
    public double getValue() {
        return znamenkaD;
    }

    @Override
    public void setValue(double value) {
        if(value<0)negative=true;
        String rez;
        if(Double.isNaN(value)){
            rez="NaN";
        }else if(Double.isInfinite(value)) {
            rez="Infinity";

        }else {
            if(negative){
                rez = Double.toString(Math.abs(value));
            }else {
                rez = Double.toString(value);
            }
        }

          znamenkaD=value;
          znamenkaS=rez;
          freezeValue(rez);
           editable=false;
        if(listener!=null) {
            this.listener.valueChanged(this);
        }

    }

    @Override
    public boolean isEditable() {
        return editable;
    }

    @Override
    public void clear() {
        editable=true;
        znamenkaS="";
        znamenkaD=0;
        if(listener!=null) {
            this.listener.valueChanged(this);
        }


    }

    @Override
    public void clearAll() {
        this.editable = true;
        this.negative = false;
        this.znamenkaS = "";
        this.znamenkaD = 0;
        this.frozen = null;
        this.isActiveOperandSet=false;
        this.activeOperand=0;
        this.pendingOperation=null;
        this.digits=0;
        clear();
        clearActiveOperand();

    }

    @Override
    public void swapSign() throws CalculatorInputException {
        if(!editable) throw new CalculatorInputException();
        negative=(!negative);
        znamenkaD*=-1;
        frozen=null;
        if(listener!=null) {
            this.listener.valueChanged(this);
        }
    }

    @Override
    public void insertDecimalPoint() throws CalculatorInputException {
        if(!editable || znamenkaS.contains(".")|| znamenkaS.length()==0) throw new CalculatorInputException();
        znamenkaS+=".";
        frozen=null;
        if(listener!=null) {
            this.listener.valueChanged(this);
        }
    }

    @Override
    public void insertDigit(int digit) throws CalculatorInputException, IllegalArgumentException {
             if(digit==0 && znamenkaD==0 && znamenkaS.length()==1){
                 return;
             }
             if(digit!=0 && znamenkaD==0  && znamenkaS.length()==1){
                 znamenkaS="";
             }

             if(!editable || digits==308) throw new CalculatorInputException();
             String s=znamenkaS;
             try {
                 s+=Integer.toString(digit);
                 znamenkaD=Double.valueOf(s);
                 if(negative){
                     znamenkaD*=-1.0;
                 }
                 znamenkaS=s;
                 digits++;
             }catch (Exception e){
                 throw new CalculatorInputException();
             }
             if(listener!=null) {
                 this.listener.valueChanged(this);
             }

    }

    @Override
    public boolean isActiveOperandSet() {
        return isActiveOperandSet;
    }

    @Override
    public double getActiveOperand() throws IllegalStateException {
        if(!isActiveOperandSet()) throw  new IllegalStateException();
        return activeOperand;
    }

    @Override
    public void setActiveOperand(double activeOperand) {
          isActiveOperandSet=true;
          this.activeOperand=activeOperand;
    }

    @Override
    public void clearActiveOperand() {
     isActiveOperandSet=false;
     activeOperand=0;
    }

    @Override
    public DoubleBinaryOperator getPendingBinaryOperation() {

        return pendingOperation;
    }

    @Override
    public void setPendingBinaryOperation(DoubleBinaryOperator op) {
        this.setActiveOperand(znamenkaD);
        znamenkaS="";
        this.pendingOperation=op;
        if(listener!=null) {
            this.listener.valueChanged(this);
        }
    }

    @Override
    public void freezeValue(String value) {
        this.frozen=value;

    }

    @Override
    public boolean hasFrozenValue() {
        return frozen!=null;
    }

    @Override
    public String toString() {
        if(!hasFrozenValue()){
            if(znamenkaS.length()==0) {
                if (negative) {
                    return "-0";
                }
                return "0";
            }else {
                if (negative) {
                    return "-" + znamenkaS;
                }
                return znamenkaS;
            }
        }
        if(negative){
            return "-"+frozen;
        }
        return frozen;
    }
}
