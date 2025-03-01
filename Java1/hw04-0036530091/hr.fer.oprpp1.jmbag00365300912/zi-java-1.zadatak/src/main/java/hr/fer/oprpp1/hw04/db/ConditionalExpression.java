package hr.fer.oprpp1.hw04.db;

public class ConditionalExpression {
   private IFieldValueGetter fieldGetter;
   private String stringLiteral;
   private IComparisonOperator comparisonOperator;

    /**
     * class constructor
     * @param fieldGetter
     * @param reference
     * @param comparisonOperator
     */
    public ConditionalExpression(IFieldValueGetter fieldGetter, String reference,  IComparisonOperator comparisonOperator) {
        this.fieldGetter = fieldGetter;
        this.stringLiteral = reference;
        this.comparisonOperator = comparisonOperator;
    }

    /**
     * @return fieldGetter
     */
    public IFieldValueGetter getFieldGetter() {
        return fieldGetter;
    }

    /**
     * @return stringLiteral
     */
    public String getStringLiteral() {
        return stringLiteral;
    }

    /**
     * @return comparisonOperator
     */
    public IComparisonOperator getComparisonOperator() {
        return comparisonOperator;
    }
}
