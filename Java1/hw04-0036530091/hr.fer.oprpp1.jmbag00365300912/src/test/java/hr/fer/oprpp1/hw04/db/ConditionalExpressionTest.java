package hr.fer.oprpp1.hw04.db;

import org.junit.jupiter.api.Test;

import javax.management.InvalidAttributeValueException;

import static org.junit.jupiter.api.Assertions.*;

class ConditionalExpressionTest {

    @Test
    void recordSatisfiesEqualsTrue() throws InvalidAttributeValueException {
        ConditionalExpression expr = new ConditionalExpression(
                FieldValueGetters.LAST_NAME,
                "Ćurić*",
                ComparisonOperators.LIKE
        );
        StudentRecord record = new StudentRecord("0000000008","Ćurić","Marko",5);;
        boolean recordSatisfies = expr.getComparisonOperator().satisfied(
                expr.getFieldGetter().get(record), // returns lastName from given record
                expr.getStringLiteral() // returns "Bos*"
        );

    }

    @Test
    void recordSatisfiesEqualsFalse() throws InvalidAttributeValueException {
        ConditionalExpression expr = new ConditionalExpression(
                FieldValueGetters.LAST_NAME,
                "Bos*",
                ComparisonOperators.LIKE
        );
        StudentRecord record = new StudentRecord("0000000008","Ćurić","Marko",5);;
        boolean recordSatisfies = expr.getComparisonOperator().satisfied(
                expr.getFieldGetter().get(record), // returns lastName from given record
                expr.getStringLiteral() // returns "Bos*"
        );

    }


}