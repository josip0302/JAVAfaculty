package hr.fer.oprpp1.custom.collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class ArrayIndexedCollectionTest {
    @Test
    public void constructor1Funcionality() {
        ArrayIndexedCollection coll=new ArrayIndexedCollection();
        assertEquals(0,coll.size());

    }
    @Test
    public void constructor2IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, ()-> new ArrayIndexedCollection(0));
    }
    @Test
    public void constructor3NullPointerException(){
        ArrayIndexedCollection coll=null;
        assertThrows(NullPointerException.class, ()-> new ArrayIndexedCollection(coll));
    }
    @Test
    public void constructor3workingProperly(){
        ArrayIndexedCollection coll=new ArrayIndexedCollection();
        coll.add(1);
        coll.add(2);
        ArrayIndexedCollection coll2=new ArrayIndexedCollection(coll);
        assertArrayEquals(coll.toArray(),coll2.toArray());
    }
    @Test
    public void constructor4NullPointerException(){
        assertThrows(NullPointerException.class, ()-> new ArrayIndexedCollection(null,1));
    }


    @Test
    public void constructor4IllegalArgumentException() {
        ArrayIndexedCollection coll=new ArrayIndexedCollection();
        coll.add(1);
        coll.add(2);
        assertThrows(IllegalArgumentException.class, ()-> new ArrayIndexedCollection(coll,0));
    }
    @Test
    public void constructor4CollectionSizeGreaterThanCapacity() {
        ArrayIndexedCollection coll=new ArrayIndexedCollection();
        coll.add(1);
        coll.add(2);
        coll.add(2);
        ArrayIndexedCollection test =new ArrayIndexedCollection(coll,2);
        assertEquals(coll.size(),test.size());
        assertArrayEquals(coll.toArray(),test.toArray());
    }
    @Test
    public void constructor4WorkingProperly() {
        Collection coll=new ArrayIndexedCollection();
        coll.add(1);
        coll.add(2);
        coll.add(2);
        ArrayIndexedCollection test =new ArrayIndexedCollection(coll,5);
        assertEquals(coll.size(),test.size());
        assertArrayEquals(coll.toArray(),test.toArray());
    }

    @Test
    void CollectionIsNotEmpty() {
        ArrayIndexedCollection coll=new ArrayIndexedCollection();
        coll.add(1);
        coll.add(2);
        assertEquals(false,coll.isEmpty());
    }
    @Test
    void CollectionIsEmpty() {
        ArrayIndexedCollection coll=new ArrayIndexedCollection();
        assertEquals(true,coll.isEmpty());
    }

    @Test
    void sizeEqualsZero() {
        ArrayIndexedCollection test=new ArrayIndexedCollection();
        assertTrue(test.size()==0);

    }
    @Test
    void sizeEqualsOne() {
        ArrayIndexedCollection test2=new ArrayIndexedCollection(20);
        test2.add(1);
        assertTrue(test2.size()==1);
    }
    @Test
    void sizeEqualsTwo() {
        ArrayIndexedCollection test2=new ArrayIndexedCollection(20);
        test2.add(1);
        test2.add(1);
        assertTrue(test2.size()==2);
    }

    @Test
    void addFunctianlityCheck() {
        ArrayIndexedCollection coll=new ArrayIndexedCollection(2);
        coll.add(1);
        coll.add(2);
        assertEquals(1,coll.get(0));
        assertEquals(2,coll.get(1));
    }
    @Test
    void addGreaterThanArraylength() {
        ArrayIndexedCollection coll=new ArrayIndexedCollection(2);
        coll.add(1);
        coll.add(2);
        coll.add(2);
        assertEquals(2,coll.get(2));
    }


    @Test
    void containsTrue() {
        ArrayIndexedCollection coll=new ArrayIndexedCollection(2);
        coll.add(1);
        coll.add(2);
        assertEquals(true,coll.contains(2));
    }
    @Test
    void containsFalse() {
        ArrayIndexedCollection coll=new ArrayIndexedCollection(2);
        coll.add(1);
        coll.add(2);
        assertEquals(false,coll.contains(5));
    }

    @Test
    void indexOfEmpty() {
        ArrayIndexedCollection coll=new ArrayIndexedCollection();
        assertEquals(-1,coll.indexOf(2));
    }
    @Test
    void indexOfNullObject() {
        ArrayIndexedCollection coll=new ArrayIndexedCollection();
        coll.add(1);
        coll.add(2);
        assertEquals(-1,coll.indexOf(null));
    }
    @Test
    void indexOfElement() {
        ArrayIndexedCollection coll=new ArrayIndexedCollection();
        coll.add(1);
        coll.add(2);
        assertEquals(1,coll.indexOf(2));
    }
    @Test
    void indexOfElementIsNotThere() {
        ArrayIndexedCollection coll=new ArrayIndexedCollection();
        coll.add(1);
        coll.add(2);
        assertEquals(-1,coll.indexOf(3));
    }


    @Test
    void removeObject() {
        ArrayIndexedCollection coll=new ArrayIndexedCollection();
        coll.add("A");
        coll.add("b");
        assertEquals(2,coll.size());
        assertTrue(coll.remove("A"));
        assertEquals("b",coll.get(0));
        assertEquals(1,coll.size());
    }

    @Test
    void toArray() {
        ArrayIndexedCollection coll=new ArrayIndexedCollection(2);
        coll.add(1);
        coll.add(2);
       Object[] test = new Object[2];
       test[0]=1;
       test[1]=2;
       assertArrayEquals(test,coll.toArray());
    }

    @Test
    void addAllAndForEachFunctianlityCheck() {
        ArrayIndexedCollection coll=new ArrayIndexedCollection(2);
        coll.add(1);
        coll.add(2);
        ArrayIndexedCollection coll2=new ArrayIndexedCollection(2);
        coll2.addAll(coll);
        assertEquals(coll.get(0),coll2.get(0));
        assertEquals(coll.get(1),coll2.get(1));

    }

    @Test
    void clearTrue() {
        ArrayIndexedCollection coll=new ArrayIndexedCollection();
        coll.add(1);
        coll.add(2);
        coll.clear();
        assertTrue(coll.isEmpty());
    }


    @Test
    void insertCorrectly() {
        ArrayIndexedCollection coll=new ArrayIndexedCollection(2);
        coll.add(1);
        coll.add(2);
        coll.insert(3,1);
        assertEquals(3,coll.get(1));
        assertEquals(2,coll.get(2));
        assertEquals(1,coll.get(0));
    }
    @Test
    void insertInCorrectly() {
        ArrayIndexedCollection coll=new ArrayIndexedCollection(2);
        coll.add(1);
        coll.add(2);
        coll.insert(5,1);
        assertThrows(IndexOutOfBoundsException.class, ()->coll.insert(5,5));

    }
    @Test
    void insertAtLastPlace() {
        ArrayIndexedCollection coll=new ArrayIndexedCollection(2);
        coll.add(1);
        coll.add(2);
        coll.insert(5,2);
        assertEquals(2,coll.get(1));
        assertEquals(5,coll.get(2));
        assertEquals(1,coll.get(0));

    }

    @Test
    void testRemoveIndexOutOfBoundsException() {
        ArrayIndexedCollection coll=new ArrayIndexedCollection();
        coll.add(1);
        coll.add(2);
        assertThrows(IndexOutOfBoundsException.class,()->coll.remove(2));
    }
}