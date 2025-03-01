package hr.fer.oprpp1.hw04.db;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QueryFilterTest {
    @Test
    void filterTest1() {
        StudentRecord record = new StudentRecord("0000000008","Ćurić","Marko",5);
        ConditionalExpression expr = new ConditionalExpression(
                FieldValueGetters.LAST_NAME,
                "Ćurić",
                ComparisonOperators.LIKE
        );
        List<ConditionalExpression> list=new ArrayList<>();
        list.add(expr);
        QueryFilter filter=new QueryFilter(list);
        assertEquals(true,filter.accepts(record));
    }
    @Test
    void filterTest2() {
        StudentRecord record = new StudentRecord("0000000008","Ćurić","Marko",5);
        ConditionalExpression expr = new ConditionalExpression(
                FieldValueGetters.LAST_NAME,
                "Ćurić",
                ComparisonOperators.LIKE
        );
        List<ConditionalExpression> list=new ArrayList<>();
        list.add(expr);
        expr = new ConditionalExpression(
                FieldValueGetters.JMBAG,
                "0000000008",
                ComparisonOperators.LIKE
        );
        list.add(expr);
        QueryFilter filter=new QueryFilter(list);
        assertEquals(true,filter.accepts(record));
    }
    @Test
    void filterTest3() {
        StudentRecord record = new StudentRecord("0000000008","Ćurić","Marko",5);
        ConditionalExpression expr = new ConditionalExpression(
                FieldValueGetters.LAST_NAME,
                "Ćuri",
                ComparisonOperators.EQUALS
        );
        List<ConditionalExpression> list=new ArrayList<>();
        list.add(expr);
        QueryFilter filter=new QueryFilter(list);
        assertEquals(false,filter.accepts(record));
    }
    @Test
    void filterTest4() {
        StudentRecord record = new StudentRecord("0000000008","Ćurić","Marko",5);
        ConditionalExpression expr = new ConditionalExpression(
                FieldValueGetters.LAST_NAME,
                "Ćuri",
                ComparisonOperators.EQUALS
        );
        List<ConditionalExpression> list=new ArrayList<>();
        list.add(expr);
        expr = new ConditionalExpression(
                FieldValueGetters.JMBAG,
                "0000000008",
                ComparisonOperators.LIKE
        );
        list.add(expr);
        QueryFilter filter=new QueryFilter(list);
        assertEquals(false,filter.accepts(record));
    }

}