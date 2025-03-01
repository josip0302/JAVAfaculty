package hr.fer.oprpp1.custom.collections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryTest {

    @Test
    void isEmptyFalse() {
        Dictionary<Integer,Integer> dictionary=new Dictionary<>();
        dictionary.put(1,1);
        dictionary.put(2,2);
        dictionary.put(3,3);
        assertEquals(false,dictionary.isEmpty());
    }

    @Test
    void isEmptyTrue() {
        Dictionary<Integer,Integer> dictionary=new Dictionary<>();
        assertEquals(true,dictionary.isEmpty());
    }
    @Test
    void size() {
        Dictionary<Integer,Integer> dictionary=new Dictionary<>();
        assertEquals(0,dictionary.size());
        dictionary.put(1,1);
        dictionary.put(2,2);
        dictionary.put(3,3);
        assertEquals(3,dictionary.size());

    }

    @Test
    void clear() {
        Dictionary<Integer,Integer> dictionary=new Dictionary<>();
        dictionary.put(1,1);
        dictionary.put(2,2);
        dictionary.put(3,3);
        dictionary.clear();
        assertEquals(true,dictionary.isEmpty());
    }

    @Test
    void get() {
        Dictionary<Integer,Integer> dictionary=new Dictionary<>();
        dictionary.put(1,1);
        dictionary.put(2,2);
        dictionary.put(3,3);
        dictionary.put(4,null);
        assertEquals(1,dictionary.get(1));
        assertEquals(2,dictionary.get(2));
        assertEquals(3,dictionary.get(3));
        assertEquals(null,dictionary.get(4));
    }

    @Test
    void put() {
        Dictionary<Integer,Integer> dictionary=new Dictionary<>();
        dictionary.put(1,1);
        dictionary.put(2,2);
        dictionary.put(3,3);
        assertEquals(1,dictionary.get(1));
        dictionary.put(1,2);
        assertEquals(2,dictionary.get(1));
        dictionary.put(1,3);
        assertEquals(3,dictionary.get(1));
        assertEquals(3,dictionary.size());
    }



    @Test
    void remove() {
        Dictionary<Integer,Integer> dictionary=new Dictionary<>();
        dictionary.put(1,1);
        dictionary.put(2,2);
        dictionary.put(3,3);
        assertEquals(1,dictionary.remove(1));
        assertEquals(null,dictionary.get(1));
    }
}