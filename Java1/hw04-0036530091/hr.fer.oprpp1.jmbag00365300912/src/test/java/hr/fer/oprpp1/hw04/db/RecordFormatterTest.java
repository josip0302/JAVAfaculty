package hr.fer.oprpp1.hw04.db;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecordFormatterTest {
    @Test
    void print() {
        List<StudentRecord> recordList=new ArrayList<>();
        recordList.add(new StudentRecord("0000000001" ,"Akšamović",  "Marin",  2));
        recordList.add(new StudentRecord("0000000002" ,"Bakamović",  "Petra",  3));
        recordList.add(new StudentRecord("0000000003" ,"Bosnić",  "Andrea",  4));
        List<String> output = RecordFormatter.format(recordList,new ArrayList<>());
        output.forEach(System.out::println);
    }
    @Test
    void printEmpty() {
        List<StudentRecord> recordList=new ArrayList<>();
        List<String> output = RecordFormatter.format(recordList,new ArrayList<>());
        output.forEach(System.out::println);
    }
}