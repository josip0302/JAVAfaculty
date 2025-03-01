package hr.fer.oprpp1.hw04.db;

import javax.management.InvalidAttributeValueException;

public interface IComparisonOperator {

    /**
     * @param value1
     * @param value2
     * @return true if condition is satisified
     * @throws InvalidAttributeValueException
     */
    public boolean satisfied(String value1, String value2) throws InvalidAttributeValueException;

}
