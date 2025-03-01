package hr.fer.oprpp1.hw04.db;

import org.junit.jupiter.api.Test;

import javax.management.InvalidAttributeValueException;

import static org.junit.jupiter.api.Assertions.*;

class ComparisonOperatorsTest {
    @Test
    void LESStest() throws InvalidAttributeValueException {
        IComparisonOperator oper = ComparisonOperators.LESS;
        assertEquals(true,oper.satisfied("Ana", "Jasna"));
        assertEquals(false,oper.satisfied("Ana", "Ana"));
        assertEquals(false,oper.satisfied("Jasna", "Ana"));
    }

    @Test
    void LESS_OR_EQUALStest() throws InvalidAttributeValueException {
        IComparisonOperator oper = ComparisonOperators.LESS_OR_EQUALS;
        assertEquals(true,oper.satisfied("Ana", "Jasna"));
        assertEquals(true,oper.satisfied("Ana", "Ana"));
        assertEquals(false,oper.satisfied("Jasna", "Ana"));
    }
    @Test
    void GREATERtest() throws InvalidAttributeValueException {
        IComparisonOperator oper = ComparisonOperators.GREATER;
        assertEquals(false,oper.satisfied("Ana", "Jasna"));
        assertEquals(false,oper.satisfied("Ana", "Ana"));
        assertEquals(true,oper.satisfied("Jasna", "Ana"));
    }
    @Test
    void GREATER_OR_EQUALStest() throws InvalidAttributeValueException {
        IComparisonOperator oper = ComparisonOperators.GREATER_OR_EQUALS;
        assertEquals(false,oper.satisfied("Ana", "Jasna"));
        assertEquals(true,oper.satisfied("Ana", "Ana"));
        assertEquals(true,oper.satisfied("Jasna", "Ana"));
    }
    @Test
    void EQUALStest() throws InvalidAttributeValueException {
        IComparisonOperator oper = ComparisonOperators.EQUALS;
        assertEquals(false,oper.satisfied("Ana", "Jasna"));
        assertEquals(true,oper.satisfied("Ana", "Ana"));
    }
    @Test
    void NOT_EQUALStest() throws InvalidAttributeValueException {
        IComparisonOperator oper = ComparisonOperators.NOT_EQUALS;
        assertEquals(true,oper.satisfied("Ana", "Jasna"));
        assertEquals(false,oper.satisfied("Ana", "Ana"));
    }


    @Test
    void LIKEtest() throws InvalidAttributeValueException {
        IComparisonOperator oper = ComparisonOperators.LIKE;

            assertEquals(false,oper.satisfied("Zagreb", "Aba*")); // false
            assertEquals(false,oper.satisfied("AAA", "AA*AA")); // false
        assertEquals(true,oper.satisfied("AA*AA", "AAAA"));

        }
    @Test
    void LIKEtest2() throws InvalidAttributeValueException {
        IComparisonOperator oper = ComparisonOperators.LIKE;

        assertEquals(true,oper.satisfied("Zagreb", "Zag*"));
        assertEquals(true,oper.satisfied("AAA", "*AA")); // false
        assertEquals(true,oper.satisfied("AA*AA", "AAAA"));

    }

    @Test
    void LIKEtestException() throws InvalidAttributeValueException {
        IComparisonOperator oper = ComparisonOperators.LIKE;
        assertThrows(InvalidAttributeValueException.class,()->oper.satisfied("AAAA", "AA**AA"));
        assertThrows(InvalidAttributeValueException.class,()->oper.satisfied("AA**AA", "AA*AA"));
    }
}