package hr.fer.oprpp1.hw04.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentDatabase {
    List<StudentRecord> studentRecordList;
    Map<String,StudentRecord> studentRecordMap;

    public StudentDatabase(List<String> databaseLine) {
        this.studentRecordList = new ArrayList<>();
        this.studentRecordMap =new HashMap<>();
        for(String s:databaseLine){
            String[] polja=s.split("\\s+");
            StudentRecord record;
            if(polja.length==4) {
                record = new StudentRecord(polja[0].trim(), polja[1].trim(), polja[2].trim(), Integer.valueOf(polja[3]));
            }else {
                record = new StudentRecord(polja[0].trim(), (polja[1]+" "+polja[2]).trim(), polja[3].trim(), Integer.valueOf(polja[4]));
            }
            studentRecordList.add(record);
            studentRecordMap.put(polja[0],record);
        }
    }

    /**
     * @return list of all studentRecords
     */
    public List<StudentRecord> getStudentRecordList() {
        return studentRecordList;
    }

    /**
     *
     * @return map of all records with jmbag as key and record as value
     */
    public Map<String, StudentRecord> getStudentRecordMap() {
        return studentRecordMap;
    }


    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        for(StudentRecord record:studentRecordList){
            sb.append(record.toString());
        }
        return sb.toString();
    }

    /**
     * @param jmbag
     * @return returns studentRecords for given jmbag
     */
    public StudentRecord forJMBAG(String jmbag){
        return studentRecordMap.get(jmbag);
    }

    /**
     * @param filter
     * @return List of studentRecords which pass the test
     */
    public List<StudentRecord> filter(IFilter filter){
        List<StudentRecord> resultList=new ArrayList<>();
        for(StudentRecord record:studentRecordList){

            if(filter.accepts(record)){

                resultList.add(record);
            }
        }
        return resultList;
    }
}
