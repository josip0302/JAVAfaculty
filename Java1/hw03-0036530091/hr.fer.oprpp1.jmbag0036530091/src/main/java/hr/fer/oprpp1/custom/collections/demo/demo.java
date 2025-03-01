package hr.fer.oprpp1.custom.collections.demo;

import hr.fer.oprpp1.custom.collections.SimpleHashtable;

import java.util.Iterator;

public class demo {
    public static void main(String[] args) {
        // create collection:
        SimpleHashtable<String,Integer> examMarks = new SimpleHashtable<>(2);
// fill data:
        examMarks.put("Ivana", 2);
        examMarks.put("Ante", 2);
        examMarks.put("Jasna", 2);
        examMarks.put("Kristina", 5);
        examMarks.put("Ivana", 5); // overwrites old grade for Ivana
        int counter=0;
        for(SimpleHashtable.TableEntry<String,Integer> pair : examMarks) {

            System.out.printf("%s => %d%n", pair.Key(), pair.Value());
        }
        Iterator<SimpleHashtable.TableEntry<String,Integer>> iter = examMarks.iterator();
        while(iter.hasNext()) {
            SimpleHashtable.TableEntry<String,Integer> pair = iter.next();
            if(pair.Key().equals("Ivana")) {
                examMarks.remove("Ivana");
            }
        }
        for(SimpleHashtable.TableEntry<String,Integer> pair : examMarks) {

            System.out.printf("%s => %d%n", pair.Key(), pair.Value());
        }

    }
}
