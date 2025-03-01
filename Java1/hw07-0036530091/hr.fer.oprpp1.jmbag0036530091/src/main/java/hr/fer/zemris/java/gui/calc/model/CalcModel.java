package hr.fer.zemris.java.gui.calc.model;

import java.util.function.DoubleBinaryOperator;

/**
 * Sučelje specificira model jednog jednostavnog kalkulatora.
 *
 * @author marcupic
 */
public interface CalcModel {
    void addCalcValueListener(CalcValueListener l);
    void removeCalcValueListener(CalcValueListener l);
    String toString();
    double getValue();
    void setValue(double value);
    boolean isEditable();
    void clear();
    void clearAll();
    void swapSign() throws CalculatorInputException;
    void insertDecimalPoint() throws CalculatorInputException;
    void insertDigit(int digit)throws CalculatorInputException, IllegalArgumentException;
    boolean isActiveOperandSet();
    double getActiveOperand()throws IllegalStateException;
    void setActiveOperand(double activeOperand);
    void clearActiveOperand();
    DoubleBinaryOperator getPendingBinaryOperation();
    void setPendingBinaryOperation(DoubleBinaryOperator op);
    void freezeValue(String value);
    boolean hasFrozenValue();
}
