package hr.fer.oprpp1.hw04.db;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueryLexerTest {
    @Test
    void nextJmbagTestReturnsNull() {
        QueryLexer lexer=new QueryLexer(" jmbag =\"0123456789\" ");
        ConditionalExpression expression=lexer.nextToken();
        assertEquals(null,lexer.nextToken());
    }
    @Test
    void nextTestString() {
        QueryLexer lexer=new QueryLexer(" jmbag =\"0123456789\" ");
        ConditionalExpression expression=lexer.nextToken();
        assertEquals("0123456789",expression.getStringLiteral());
        assertEquals(null,lexer.nextToken());
    }
    @Test
    void nextComparisonOperatorTest1() {
        QueryLexer lexer=new QueryLexer("query jmbag =\"0123456789\" ");
        ConditionalExpression expression=lexer.nextToken();
        assertEquals(ComparisonOperators.EQUALS,expression.getComparisonOperator());
        assertEquals(null,lexer.nextToken());
    }
    @Test
    void nextComparisonOperatorTest2() {
        QueryLexer lexer=new QueryLexer("query jmbag >\"0123456789\" ");
        ConditionalExpression expression=lexer.nextToken();
        assertEquals(ComparisonOperators.GREATER,expression.getComparisonOperator());
        assertEquals(null,lexer.nextToken());
    }
    @Test
    void nextComparisonOperatorTest3() {
        QueryLexer lexer=new QueryLexer("query jmbag >=\"0123456789\" ");
        ConditionalExpression expression=lexer.nextToken();
        assertEquals(ComparisonOperators.GREATER_OR_EQUALS,expression.getComparisonOperator());
        assertEquals(null,lexer.nextToken());
    }
    @Test
    void nextComparisonOperatorTest4() {
        QueryLexer lexer=new QueryLexer("query jmbag <\"0123456789\" ");
        ConditionalExpression expression=lexer.nextToken();
        assertEquals(ComparisonOperators.LESS,expression.getComparisonOperator());
        assertEquals(null,lexer.nextToken());
    }
    @Test
    void nextComparisonOperatorTest5() {
        QueryLexer lexer=new QueryLexer("query jmbag <=\"0123456789\" ");
        ConditionalExpression expression=lexer.nextToken();
        assertEquals(ComparisonOperators.LESS_OR_EQUALS,expression.getComparisonOperator());
        assertEquals(null,lexer.nextToken());
    }
    @Test
    void nextComparisonOperatorTest6() {
        QueryLexer lexer=new QueryLexer("query jmbag LIKE \"0123456789\" ");
        ConditionalExpression expression=lexer.nextToken();
        assertEquals(ComparisonOperators.LIKE,expression.getComparisonOperator());
        assertEquals(null,lexer.nextToken());
    }

    @Test
    void nextFieldGetterTest1() {
        QueryLexer lexer=new QueryLexer("query jmbag =\"0123456789\" ");
        ConditionalExpression expression=lexer.nextToken();
        assertEquals(FieldValueGetters.JMBAG,expression.getFieldGetter());
        assertEquals(null,lexer.nextToken());
    }
    @Test
    void nextFieldGetterTest2() {
        QueryLexer lexer=new QueryLexer("query firstName =\"0123456789\" ");
        ConditionalExpression expression=lexer.nextToken();
        assertEquals(FieldValueGetters.FIRST_NAME,expression.getFieldGetter());
        assertEquals(null,lexer.nextToken());
    }
    @Test
    void nextFieldGetterTest3() {
        QueryLexer lexer=new QueryLexer("query lastName =\"0123456789\" ");
        ConditionalExpression expression=lexer.nextToken();
        assertEquals(FieldValueGetters.LAST_NAME,expression.getFieldGetter());
        assertEquals(null,lexer.nextToken());
    }
    @Test
    void andTest() {
        QueryLexer lexer=new QueryLexer("jmbag=\"0123456789\" and lastName>\"J\"");
        ConditionalExpression expression=lexer.nextToken();
        assertEquals(FieldValueGetters.JMBAG,expression.getFieldGetter());
        assertEquals(ComparisonOperators.EQUALS,expression.getComparisonOperator());
        assertEquals("0123456789",expression.getStringLiteral());
         expression=lexer.nextToken();
        assertEquals(FieldValueGetters.LAST_NAME,expression.getFieldGetter());
        assertEquals(ComparisonOperators.GREATER,expression.getComparisonOperator());
        assertEquals("J",expression.getStringLiteral());
        assertEquals(null,lexer.nextToken());
    }

}