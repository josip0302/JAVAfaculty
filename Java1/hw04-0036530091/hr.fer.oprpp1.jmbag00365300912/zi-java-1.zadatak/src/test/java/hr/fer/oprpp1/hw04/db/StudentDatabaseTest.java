package hr.fer.oprpp1.hw04.db;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentDatabaseTest {
    @Test
    void CounstructorTest1(){
        List<String> list=new ArrayList<>();
        list.add("0000000001   Akšamović  Marin  2");
        list.add("0000000002   Bakamović  Petra  3");
        list.add("0000000003 Bosnić Andrea 4");
        StudentDatabase studentDatabase=new StudentDatabase(list);
        List<StudentRecord> recordList=new ArrayList<>();
        recordList.add(new StudentRecord("0000000001" ,"Akšamović",  "Marin",  2));
        recordList.add(new StudentRecord("0000000002" ,"Bakamović",  "Petra",  3));
        recordList.add(new StudentRecord("0000000003" ,"Bosnić",  "Andrea",  4));
        System.out.println(studentDatabase.toString());
        assertArrayEquals(recordList.toArray(),studentDatabase.getStudentRecordList().toArray());
    }
    @Test
    void CounstructorTest2(){
        List<String> list=new ArrayList<>();
        list.add("0000000001   Akšamović  Marin  2");
        list.add("0000000002   Bakamović Bakamović Petra  3");
        list.add("0000000003 Bosnić Andrea 4");
        StudentDatabase studentDatabase=new StudentDatabase(list);
        List<StudentRecord> recordList=new ArrayList<>();
        recordList.add(new StudentRecord("0000000001" ,"Akšamović",  "Marin",  2));
        recordList.add(new StudentRecord("0000000002" ,"Bakamović Bakamović",  "Petra",  3));
        recordList.add(new StudentRecord("0000000003" ,"Bosnić",  "Andrea",  4));
        System.out.println(studentDatabase.toString());
        assertEquals("Bakamović Bakamović",studentDatabase.getStudentRecordList().get(1).getLastName());
    }
    @Test
    void forJMBAGTest(){
        List<String> list=new ArrayList<>();
        list.add("0000000001   Akšamović  Marin  2");
        list.add("0000000002   Bakamović Bakamović Petra  3");
        list.add("0000000003 Bosnić Andrea 4");
        StudentDatabase studentDatabase=new StudentDatabase(list);
        assertEquals("Bakamović Bakamović",studentDatabase.forJMBAG("0000000002").getLastName());
        assertEquals("Bosnić",studentDatabase.forJMBAG("0000000003").getLastName());
        assertEquals("Akšamović",studentDatabase.forJMBAG("0000000001").getLastName());
    }
    @Test
    void filterTest(){
        List<String> list=new ArrayList<>();
        list.add("0000000001   Akšamović  Marin  2");
        list.add("0000000002   Bakamović Bakamović Petra  3");
        list.add("0000000003 Bosnić Andrea 4");
        StudentDatabase studentDatabase=new StudentDatabase(list);
        List<StudentRecord> newlist=studentDatabase.filter( (IFilter) record -> record.getJmbag().equals("0000000002")
        );
        System.out.println(newlist.toString());
        assertEquals(1,newlist.size());
        assertEquals("Bakamović Bakamović",newlist.get(0).getLastName());
    }

}