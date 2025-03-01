package hr.fer.oprpp1.custom.collections;

import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class SimpleHashtableTest {
    @Test
    void counstructorIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,()->new SimpleHashtable<Integer,Integer>(0));

    }

    @Test
    void putAndGet() {
        SimpleHashtable<Integer,Integer> simpleHashtable=new SimpleHashtable<>();
        assertEquals(true,simpleHashtable.isEmpty());

        simpleHashtable.put(1,1);
        assertEquals(1,simpleHashtable.get(1));
        assertEquals(false,simpleHashtable.isEmpty());
    }

    @Test
    void putAndGetMoreDifferent() {
        SimpleHashtable<Integer,Integer> simpleHashtable=new SimpleHashtable<>(25);
        simpleHashtable.put(1,1);
        simpleHashtable.put(3,3);
        simpleHashtable.put(2,2);
        assertEquals(1,simpleHashtable.get(1));
        assertEquals(2,simpleHashtable.get(2));
        assertEquals(3,simpleHashtable.get(3));
    }

    @Test
    void putAndGetMoreSame() {
        SimpleHashtable<Integer,Integer> simpleHashtable=new SimpleHashtable<>(1);
        simpleHashtable.put(1,1);
        simpleHashtable.put(3,3);
        simpleHashtable.put(2,2);
        assertEquals(1,simpleHashtable.get(1));
        assertEquals(2,simpleHashtable.get(2));
        assertEquals(3,simpleHashtable.get(3));
        simpleHashtable.put(3,5);
        assertEquals(3,simpleHashtable.size());
        assertEquals(5,simpleHashtable.get(3));

    }
    @Test
    void removeFirstFromOneSlotWithBigCapacity() {
        SimpleHashtable<Integer,Integer> simpleHashtable=new SimpleHashtable<>(20);
        simpleHashtable.put(1,1);
        simpleHashtable.put(3,3);
        simpleHashtable.put(2,2);
        assertEquals(1,simpleHashtable.get(1));
        assertEquals(1, simpleHashtable.remove(1));
        assertEquals(null,simpleHashtable.get(1));

    }
    @Test
    void removeFirstFromOneSlotWithSmallCapacity() {
        SimpleHashtable<Integer,Integer> simpleHashtable=new SimpleHashtable<>(1);
        simpleHashtable.put(1,1);
        simpleHashtable.put(3,3);
        simpleHashtable.put(2,2);
        assertEquals(1,simpleHashtable.get(1));
        assertEquals(1, simpleHashtable.remove(1));
        assertEquals(null,simpleHashtable.get(1));

    }
    @Test
    void removeSecondFromOneSlotWithSmallCapacity() {
        SimpleHashtable<Integer,Integer> simpleHashtable=new SimpleHashtable<>(1);
        simpleHashtable.put(1,1);
        simpleHashtable.put(3,3);
        simpleHashtable.put(2,2);
        assertEquals(3,simpleHashtable.get(3));
        assertEquals(3, simpleHashtable.remove(3));
        assertEquals(null,simpleHashtable.get(3));

    }
    @Test
    void removeSecondFromOneSlotWithBigCapacity() {
        SimpleHashtable<Integer,Integer> simpleHashtable=new SimpleHashtable<>(20);
        simpleHashtable.put(1,1);
        simpleHashtable.put(3,3);
        simpleHashtable.put(2,2);
        assertEquals(3,simpleHashtable.get(3));
        assertEquals(3, simpleHashtable.remove(3));
        assertEquals(null,simpleHashtable.get(3));

    }
    @Test
    void removeLastFromOneSlotWithSmallCapacity() {
        SimpleHashtable<Integer,Integer> simpleHashtable=new SimpleHashtable<>(1);
        simpleHashtable.put(1,1);
        simpleHashtable.put(3,3);
        simpleHashtable.put(2,2);
        assertEquals(2,simpleHashtable.get(2));
        assertEquals(2, simpleHashtable.remove(2));
        assertEquals(null,simpleHashtable.get(2));

    }
    @Test
    void removeLastFromOneSlotWithBigCapacity() {
        SimpleHashtable<Integer,Integer> simpleHashtable=new SimpleHashtable<>(20);
        simpleHashtable.put(1,1);
        simpleHashtable.put(3,3);
        simpleHashtable.put(2,2);
        assertEquals(2,simpleHashtable.get(2));
        assertEquals(2, simpleHashtable.remove(2));
        assertEquals(null,simpleHashtable.get(2));

    }
    @Test
    void removeNotTHEREWithBigCapacity() {
        SimpleHashtable<Integer,Integer> simpleHashtable=new SimpleHashtable<>(20);
        simpleHashtable.put(1,1);
        simpleHashtable.put(3,3);
        simpleHashtable.put(2,2);
        assertEquals(2,simpleHashtable.get(2));
        assertEquals(null, simpleHashtable.remove(5));
        assertEquals(2,simpleHashtable.get(2));

    }
    @Test
    void removeNotTHEREWithSmallCapacity() {
        SimpleHashtable<Integer,Integer> simpleHashtable=new SimpleHashtable<>(1);
        simpleHashtable.put(1,1);
        simpleHashtable.put(3,3);
        simpleHashtable.put(2,2);
        assertEquals(2,simpleHashtable.get(2));
        assertEquals(null, simpleHashtable.remove(5));
        assertEquals(2,simpleHashtable.get(2));

    }
    @Test
    void removeFromMoreSlotsWithBigCapacity() {
        SimpleHashtable<Integer,Integer> simpleHashtable=new SimpleHashtable<>(30);
        simpleHashtable.put(1,1);
        simpleHashtable.put(3,3);
        simpleHashtable.put(2,2);
        assertEquals(3,simpleHashtable.size());
        assertEquals(2,simpleHashtable.get(2));
        assertEquals(2, simpleHashtable.remove(2));
        assertEquals(null,simpleHashtable.get(2));
        assertEquals(3,simpleHashtable.get(3));
        assertEquals(3, simpleHashtable.remove(3));
        assertEquals(null,simpleHashtable.get(3));
        assertEquals(1,simpleHashtable.get(1));
        assertEquals(1, simpleHashtable.remove(1));
        assertEquals(null,simpleHashtable.get(1));
        assertEquals(0,simpleHashtable.size());

    }
    @Test
    void removeFromMoreSlotsWithSmallCapacity() {
        SimpleHashtable<Integer,Integer> simpleHashtable=new SimpleHashtable<>(30);
        simpleHashtable.put(1,1);
        simpleHashtable.put(3,3);
        simpleHashtable.put(2,2);
        assertEquals(3,simpleHashtable.size());
        assertEquals(2,simpleHashtable.get(2));
        assertEquals(2, simpleHashtable.remove(2));
        assertEquals(null,simpleHashtable.get(2));
        assertEquals(3,simpleHashtable.get(3));
        assertEquals(3, simpleHashtable.remove(3));
        assertEquals(null,simpleHashtable.get(3));
        assertEquals(1,simpleHashtable.get(1));
        assertEquals(1, simpleHashtable.remove(1));
        assertEquals(null,simpleHashtable.get(1));
        assertEquals(0,simpleHashtable.size());

    }
    @Test
    void containsKeyTestWithSmallCapacity() {
        SimpleHashtable<Integer, Integer> simpleHashtable = new SimpleHashtable<>(1);
        simpleHashtable.put(1, 1);
        simpleHashtable.put(3, 3);
        simpleHashtable.put(2, 2);
        assertTrue(simpleHashtable.containsKey(1));
        assertTrue(simpleHashtable.containsKey(2));
        assertTrue(simpleHashtable.containsKey(3));
        assertFalse(simpleHashtable.containsKey(4));
    }
    @Test
    void containsKeyTestWithBigCapacity() {
        SimpleHashtable<Integer, Integer> simpleHashtable = new SimpleHashtable<>(20);
        simpleHashtable.put(1, 1);
        simpleHashtable.put(3, 3);
        simpleHashtable.put(2, 2);
        assertTrue(simpleHashtable.containsKey(1));
        assertTrue(simpleHashtable.containsKey(2));
        assertTrue(simpleHashtable.containsKey(3));
        assertFalse(simpleHashtable.containsKey(4));
    }
    @Test
    void containsValueTestWithSmallCapacity() {
        SimpleHashtable<Integer, Integer> simpleHashtable = new SimpleHashtable<>(1);
        simpleHashtable.put(1, 1);
        simpleHashtable.put(3, 3);
        simpleHashtable.put(2, 2);
        simpleHashtable.put(5,null);
        assertTrue(simpleHashtable.containsValue(1));
        assertTrue(simpleHashtable.containsValue(2));
        assertTrue(simpleHashtable.containsValue(3));
        assertEquals(null,simpleHashtable.get(5));
        assertEquals(4,simpleHashtable.size());
        assertFalse(simpleHashtable.containsValue(4));

    }
    @Test
    void containsValueTestWithBigCapacity() {
        SimpleHashtable<Integer, Integer> simpleHashtable = new SimpleHashtable<>(20);
        simpleHashtable.put(1, 1);
        simpleHashtable.put(3, 3);
        simpleHashtable.put(2, 2);
        simpleHashtable.put(5,null);
        assertTrue(simpleHashtable.containsValue(1));
        assertTrue(simpleHashtable.containsValue(2));
        assertTrue(simpleHashtable.containsValue(3));
        assertEquals(null,simpleHashtable.get(5));
        assertEquals(4,simpleHashtable.size());
        assertFalse(simpleHashtable.containsValue(4));

    }
    @Test
    void NotContainsValueTestWithBigCapacity() {
        SimpleHashtable<Integer, Integer> simpleHashtable = new SimpleHashtable<>(20);
        simpleHashtable.put(1, 1);
        simpleHashtable.put(3, 3);
        simpleHashtable.put(2, 2);
        simpleHashtable.put(5,null);
        assertFalse(simpleHashtable.containsValue(4));

    }
    @Test
    void NotContainsValueTestWithSmallCapacity() {
        SimpleHashtable<Integer, Integer> simpleHashtable = new SimpleHashtable<>(1);
        simpleHashtable.put(1, 1);
        simpleHashtable.put(3, 3);
        simpleHashtable.put(2, 2);
        simpleHashtable.put(5,null);
        assertFalse(simpleHashtable.containsValue(4));

    }
    @Test
    void containsNullValueTestWithSmallCapacity(){
        SimpleHashtable<Integer, Integer> simpleHashtable = new SimpleHashtable<>(1);
        simpleHashtable.put(1, 1);
        simpleHashtable.put(3, 3);
        simpleHashtable.put(2, 2);
        simpleHashtable.put(5,null);
        assertTrue(simpleHashtable.containsValue(null));
    }
    @Test
    void containsNullValueTestWithBigCapacity(){
        SimpleHashtable<Integer, Integer> simpleHashtable = new SimpleHashtable<>(20);
        simpleHashtable.put(1, 1);
        simpleHashtable.put(3, 3);
        simpleHashtable.put(2, 2);
        simpleHashtable.put(5,null);
        assertTrue(simpleHashtable.containsValue(null));
    }
    @Test
    void toArrayAndToStringWithBigCapacity() {
        SimpleHashtable<Integer, Integer> simpleHashtable = new SimpleHashtable<>(20);
        simpleHashtable.put(1, 1);
        simpleHashtable.put(3, 3);
        simpleHashtable.put(2, 2);
        simpleHashtable.put(5, null);
        System.out.println(simpleHashtable.toString());
    }
    @Test
    void toArrayAndToStringWithSmallCapacity() {
        SimpleHashtable<Integer, Integer> simpleHashtable = new SimpleHashtable<>(1);
        simpleHashtable.put(1, 1);
        simpleHashtable.put(3, 3);
        simpleHashtable.put(2, 2);
        simpleHashtable.put(5, null);
        System.out.println(simpleHashtable.toString());
    }
    @Test
    void clearTestWithSmallCapacity() {
        SimpleHashtable<Integer, Integer> simpleHashtable = new SimpleHashtable<>(1);
        simpleHashtable.put(1, 1);
        simpleHashtable.put(3, 3);
        simpleHashtable.put(2, 2);
        simpleHashtable.put(5, null);
        System.out.println(simpleHashtable.toString());
        assertEquals(4,simpleHashtable.size());
        simpleHashtable.clear();
        System.out.println(simpleHashtable.toString());
        assertEquals(0,simpleHashtable.size());
    }
    @Test
    void clearTestWithBigCapacity() {
        SimpleHashtable<Integer, Integer> simpleHashtable = new SimpleHashtable<>(20);
        simpleHashtable.put(1, 1);
        simpleHashtable.put(3, 3);
        simpleHashtable.put(2, 2);
        simpleHashtable.put(5, null);
        System.out.println(simpleHashtable.toString());
        assertEquals(4,simpleHashtable.size());
        simpleHashtable.clear();
        System.out.println(simpleHashtable.toString());
        assertEquals(0,simpleHashtable.size());
    }
    @Test
    void IteratorThrowsIllegalStateException() {
        SimpleHashtable<String,Integer> examMarks = new SimpleHashtable<>(2);
// fill data:
        examMarks.put("Ivana", 2);
        examMarks.put("Ante", 2);
        examMarks.put("Jasna", 2);
        examMarks.put("Kristina", 5);
        examMarks.put("Ivana", 5); // overwrites old grade for Ivana
        Iterator<SimpleHashtable.TableEntry<String,Integer>> iter = examMarks.iterator();
        assertThrows(IllegalStateException.class,()-> {
                    while (iter.hasNext()) {
                        SimpleHashtable.TableEntry<String, Integer> pair = iter.next();
                        if (pair.Key().equals("Ivana")) {
                            iter.remove();
                            iter.remove();
                        }
                    }
                }

        );

    }
    @Test
    void IteratorThrowsConcurrentModificationException () {
        SimpleHashtable<String,Integer> examMarks = new SimpleHashtable<>(2);
// fill data:
        examMarks.put("Ivana", 2);
        examMarks.put("Ante", 2);
        examMarks.put("Jasna", 2);
        examMarks.put("Kristina", 5);
        examMarks.put("Ivana", 5); // overwrites old grade for Ivana
        Iterator<SimpleHashtable.TableEntry<String,Integer>> iter = examMarks.iterator();
        assertThrows(ConcurrentModificationException.class,()-> {
                    while (iter.hasNext()) {
                        SimpleHashtable.TableEntry<String, Integer> pair = iter.next();
                        if (pair.Key().equals("Ivana")) {
                            examMarks.remove("Ivana");

                        }
                    }
                }

        );

    }
    @Test
    void IteratorRemoveTest () {
        SimpleHashtable<String,Integer> examMarks = new SimpleHashtable<>(2);
// fill data:
        examMarks.put("Ivana", 2);
        examMarks.put("Ante", 2);
        examMarks.put("Jasna", 2);
        examMarks.put("Kristina", 5);
        examMarks.put("Ivana", 5); // overwrites old grade for Ivana
        Iterator<SimpleHashtable.TableEntry<String,Integer>> iter = examMarks.iterator();

        while (iter.hasNext()) {
            SimpleHashtable.TableEntry<String, Integer> pair = iter.next();
            if (pair.Key().equals("Ivana")) {
                iter.remove();

            }
        }
        assertEquals(null,examMarks.get("Ivana"));
    }

    @Test
    void IteratorNextTest () {
        SimpleHashtable<String,Integer> examMarks = new SimpleHashtable<>(2);
// fill data:
        examMarks.put("Ivana", 2);
        examMarks.put("Ante", 2);
        examMarks.put("Jasna", 2);
        examMarks.put("Kristina", 5);
        examMarks.put("Ivana", 5); // overwrites old grade for Ivana
        Iterator<SimpleHashtable.TableEntry<String,Integer>> iter = examMarks.iterator();

        assertEquals("Ante",iter.next().Key());
        assertEquals("Ivana",iter.next().Key());
        assertEquals("Jasna",iter.next().Key());
        assertEquals("Kristina",iter.next().Key());
        assertEquals(false,iter.hasNext());
    }



}