package hr.fer.oprpp1.custom.collections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListIndexedCollectionTest {

    @Test
    void ConstructorAddAndGetTest() {
        LinkedListIndexedCollection coll=new LinkedListIndexedCollection();
        coll.add(3);
        coll.add(4);
        coll.add(5);
        coll.add(5);
        coll.add(5);
        assertEquals(4,coll.get(1));
        assertEquals(3,coll.get(0));
        assertEquals(5,coll.get(2));
    }

    @Test
    void GetIndexOutOfBoundsException(){
        LinkedListIndexedCollection coll=new LinkedListIndexedCollection();
        coll.add(3);
        coll.add(4);
        coll.add(5);

        assertThrows(IndexOutOfBoundsException.class,()->coll.get(3));
    }

    @Test
    void sizeTest() {
        LinkedListIndexedCollection coll=new LinkedListIndexedCollection();
        assertEquals(0,coll.size());
        coll.add(3);
        assertEquals(1,coll.size());
        coll.add(4);
        assertEquals(2,coll.size());
        coll.add(5);
        assertEquals(3,coll.size());
    }



    @Test
    void containsTest() {
        LinkedListIndexedCollection coll=new LinkedListIndexedCollection();
        coll.add(3);
        coll.add(4);
        coll.add(5);
        assertEquals(true,coll.contains(3));
        assertEquals(false,coll.contains(1));
        assertEquals(true,coll.contains(5));
        assertEquals(false,coll.contains(6));
    }

    @Test
    void removefirst() {
        LinkedListIndexedCollection coll=new LinkedListIndexedCollection();
        coll.add(1);
        coll.add(2);
        coll.add(3);
        coll.add(4);
        coll.add(5);
        coll.add(6);
        assertEquals(6,coll.size());
        coll.remove(0);
        assertEquals(2,coll.get(0));
        assertEquals(5,coll.size());

    }
    @Test
    void removeLast() {
        LinkedListIndexedCollection coll=new LinkedListIndexedCollection();
        coll.add(1);
        coll.add(2);
        coll.add(3);
        coll.add(4);
        coll.add(5);
        coll.add(6);
        assertEquals(6,coll.size());
        coll.remove(5);
        assertEquals(5,coll.get(4));
        assertEquals(5,coll.size());
    }
    @Test
    void removeMiddle() {
        LinkedListIndexedCollection coll=new LinkedListIndexedCollection();
        coll.add(1);
        coll.add(2);
        coll.add(3);
        coll.add(4);
        coll.add(5);
        coll.add(6);
        assertEquals(6,coll.size());
        coll.remove(3);
        assertEquals(3,coll.get(2));
        assertEquals(5,coll.get(3));
        assertEquals(5,coll.size());
    }
    @Test
    void removeIndexOutOfBoundsException() {
        LinkedListIndexedCollection coll=new LinkedListIndexedCollection();
        coll.add(1);
        coll.add(2);
        coll.add(3);
        coll.add(4);
        coll.add(5);
        coll.add(6);
        assertThrows(IndexOutOfBoundsException.class,()->coll.remove(6));
    }


    @Test
    void SecondConstructortoArrayAndAddAllTest() {
        LinkedListIndexedCollection coll=new LinkedListIndexedCollection();
        coll.add(3);
        coll.add(4);
        coll.add(5);
        LinkedListIndexedCollection coll2=new LinkedListIndexedCollection(coll);
        ArrayIndexedCollection coll3= new ArrayIndexedCollection();
        coll3.add(3);
        coll3.add(4);
        coll3.add(5);
        LinkedListIndexedCollection coll4=new LinkedListIndexedCollection(coll3);
        assertArrayEquals(coll.toArray(),coll2.toArray());
        assertArrayEquals(coll3.toArray(), coll4.toArray());

    }
    @Test
    void seconConstructorthrowsNullPointerException(){
        assertThrows(NullPointerException.class,()->new LinkedListIndexedCollection(null));
    }

    @Test
    void forEachTest() {
        LinkedListIndexedCollection coll=new LinkedListIndexedCollection();
        coll.add(3);
        coll.add(4);

        Processor pro = new Processor(){
            @Override
            public void process(Object value){
                System.out.println(value);

            }
        };
        coll.forEach(pro);
        coll.add(5);
        coll.forEach(pro);


    }

    @Test
    void addAll() {
        LinkedListIndexedCollection coll=new LinkedListIndexedCollection();
        coll.add(3);
        coll.add(4);
        coll.add(5);
        LinkedListIndexedCollection coll2=new LinkedListIndexedCollection();
        coll2.addAll(coll);
        assertArrayEquals(coll.toArray(),coll2.toArray());

    }

    @Test
    void clear() {
        LinkedListIndexedCollection coll=new LinkedListIndexedCollection();
        coll.add(1);
        coll.clear();
        assertTrue(coll.isEmpty());
    }
    @Test
    void IsEmpty(){
        LinkedListIndexedCollection coll=new LinkedListIndexedCollection();
        assertTrue(coll.isEmpty());
    }
    @Test
    void IsNotEmpty(){
        LinkedListIndexedCollection coll=new LinkedListIndexedCollection();
        coll.add(1);
        assertFalse(coll.isEmpty());
    }
    @Test
    void indexOfTest(){
        LinkedListIndexedCollection coll=new LinkedListIndexedCollection();
        coll.add(3);
        coll.add(4);
        coll.add(5);
        assertEquals(0,coll.indexOf(3));
        assertEquals(1,coll.indexOf(4));
        assertEquals(2,coll.indexOf(5));
        assertEquals(-1,coll.indexOf(2));
    }
    @Test
    void removeWithObjectMiddleTest(){
        LinkedListIndexedCollection coll=new LinkedListIndexedCollection();
        coll.add("A");
        coll.add("B");
        coll.add("C");
        coll.add("D");

        assertEquals(4,coll.size());
        assertTrue(coll.remove("C"));
        assertEquals("B",coll.get(1));
        assertEquals("D",coll.get(2));
        assertEquals(3,coll.size());

    }
    @Test
    void removeWithObjectFirstTest(){
        LinkedListIndexedCollection coll=new LinkedListIndexedCollection();
        coll.add("A");
        coll.add("B");
        coll.add("C");
        coll.add("D");

        assertEquals(4,coll.size());
        assertTrue(coll.remove("A"));
        assertEquals("B",coll.get(0));
        assertEquals("C",coll.get(1));
        assertEquals(3,coll.size());
    }
    @Test
    void removeWithObjectLastTest(){
        LinkedListIndexedCollection coll=new LinkedListIndexedCollection();
        coll.add("A");
        coll.add("B");
        coll.add("C");
        coll.add("D");

        assertEquals(4,coll.size());
        assertTrue(coll.remove("D"));
        assertEquals("B",coll.get(1));
        assertEquals("C",coll.get(2));
        assertEquals(3,coll.size());
    }
    @Test
    void removeWithObjectNotThereTest(){
        LinkedListIndexedCollection coll=new LinkedListIndexedCollection();
        coll.add("A");
        coll.add("B");
        coll.add("C");
        coll.add("D");

        assertEquals(4,coll.size());
        assertFalse(coll.remove("Z"));
        assertEquals("B",coll.get(1));
        assertEquals("C",coll.get(2));
        assertEquals(4,coll.size());
    }
    @Test
    void containsNullPointerException(){
        LinkedListIndexedCollection coll=new LinkedListIndexedCollection();
        coll.add("A");
        coll.add("B");
        coll.add("C");
        coll.add("D");
        assertThrows(NullPointerException.class,()->coll.contains(null));

    }
    @Test
    void insertFirst() {
        LinkedListIndexedCollection coll=new LinkedListIndexedCollection();
        coll.add(1);
        coll.add(2);
        coll.add(3);
        coll.add(4);
        coll.add(5);
        coll.add(6);
        assertEquals(6,coll.size());
       coll.insert(0,0);
        assertEquals(7,coll.size());
        assertEquals(0,coll.get(0));
        assertEquals(1,coll.get(1));
    }
    @Test
    void insertLast() {
        LinkedListIndexedCollection coll=new LinkedListIndexedCollection();
        coll.add(1);
        coll.add(2);
        coll.add(3);
        coll.add(4);
        coll.add(5);
        coll.add(6);
        assertEquals(6,coll.size());
        coll.insert(7,6);
        assertEquals(7,coll.size());
        assertEquals(7,coll.get(6));
        assertEquals(6,coll.get(5));
    }

    @Test
    void insertMiddle() {
        LinkedListIndexedCollection coll=new LinkedListIndexedCollection();
        coll.add(1);
        coll.add(2);
        coll.add(3);
        coll.add(4);
        coll.add(5);
        coll.add(6);
        assertEquals(6,coll.size());
        coll.insert(10,3);
        assertEquals(7,coll.size());
        assertEquals(3,coll.get(2));
        assertEquals(10,coll.get(3));
        assertEquals(4,coll.get(4));
    }
    @Test
    void insertIndexOutOfBoundsException() {
        LinkedListIndexedCollection coll=new LinkedListIndexedCollection();
        coll.add(1);
        coll.add(2);
        coll.add(3);
        coll.add(4);
        coll.add(5);
        coll.add(6);
       assertThrows(IndexOutOfBoundsException.class,()->coll.insert(8,7));
        assertThrows(IndexOutOfBoundsException.class,()->coll.insert(8,-1));

    }
    @Test
    void insertNullPointerException() {
        LinkedListIndexedCollection coll=new LinkedListIndexedCollection();
        coll.add(1);
        coll.add(2);
        coll.add(3);
        coll.add(4);
        coll.add(5);
        coll.add(6);
        assertThrows(NullPointerException.class,()->coll.insert(null,5));

    }

}