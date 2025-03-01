package hr.fer.oprpp1.hw04.db;

import java.util.ArrayList;
import java.util.List;

public class RecordFormatter {

    /**
     * @param recordList
     * @return list of formated strings
     */
    public static List<String> format(List<StudentRecord> recordList,List<String> showing){
        boolean lastname=true;
        boolean firstname=true;
        boolean jmbag=true;
        if(showing.size()!=0){
            if(!showing.contains("firstName")){
                firstname=false;
            }
            if(!showing.contains("lastName")){
                lastname=false;
            }
            if(!showing.contains("jmbag")){
                jmbag=false;
            }
        }

        int firstNameLength=0;
        int lastNameLength=0;
        List<String> result=new ArrayList<>();
        if(recordList.size()==0) return result;
        for(StudentRecord record:recordList){
            if(record.getFirstName().length()>firstNameLength){
                firstNameLength=record.getFirstName().length();
            }
            if(record.getLastName().length()>lastNameLength){
                lastNameLength=record.getLastName().length();
            }
        }
        StringBuilder builder=new StringBuilder();
        if(jmbag){
        builder.append('+');
        for(int i=0;i<12;i++){
            builder.append('=');
        }}
        if(lastname){
        builder.append('+');
        for(int i=0;i<lastNameLength+2;i++){
            builder.append('=');
        }}
        if(firstname){
        builder.append('+');
        for(int i=0;i<firstNameLength+2;i++){
            builder.append('=');
        }}
        builder.append("+===+");
        String startend=builder.toString();
        result.add(builder.toString());
        for(StudentRecord record:recordList) {
            builder=new StringBuilder();
            if(jmbag) {
                builder.append("| " + record.getJmbag() + " ");
            }
            if(lastname){
            builder.append("| "+record.getLastName()+" ");
            for(int i=0;i<(lastNameLength-record.getLastName().length());i++){
                builder.append(" ");
            }}
            if(firstname){
            builder.append("| "+record.getFirstName()+" ");
            for(int i=0;i<(firstNameLength-record.getFirstName().length());i++){
                builder.append(" ");
            }}
            builder.append("| "+record.getFinalGrade()+" |");

            result.add(builder.toString());
        }
        result.add(startend);
       return result;

    }
}
