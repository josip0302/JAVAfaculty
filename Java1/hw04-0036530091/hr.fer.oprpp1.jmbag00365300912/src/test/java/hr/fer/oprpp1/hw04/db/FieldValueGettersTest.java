package hr.fer.oprpp1.hw04.db;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldValueGettersTest {
    @Test
    void FIRST_NAMEtest() {
        StudentRecord record = new StudentRecord("0000000008","Ćurić","Marko",5);
        assertEquals("Marko",FieldValueGetters.FIRST_NAME.get(record));
    }
    @Test
    void LAST_NAMEtest() {
        StudentRecord record = new StudentRecord("0000000008","Ćurić","Marko",5);
        assertEquals("Ćurić",FieldValueGetters.LAST_NAME.get(record));
    }
    @Test
    void JMBAGtest() {
        StudentRecord record = new StudentRecord("0000000008","Ćurić","Marko",5);
        assertEquals("0000000008",FieldValueGetters.JMBAG.get(record));
    }

}