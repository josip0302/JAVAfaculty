package hr.fer.oprpp1.custom.collections;

import java.util.ConcurrentModificationException;

public class ArrayIndexedCollection implements List{
private int size;
private Object[] elements;
private long modificationCount;

    public ArrayIndexedCollection() {
        this.size=0;
        this.elements=new Object[16];
        this.modificationCount=0;
    }

    /**
     *
     * @param initialCapacity
     * @throws IllegalArgumentException if initalcapacity is lower than 1
     */
    public ArrayIndexedCollection(int initialCapacity) throws IllegalArgumentException{
        if(initialCapacity<1) throw
                new IllegalArgumentException("Initial capacity must be greater than 0");
        this.size = 0;
        this.elements=new Object[initialCapacity];
        this.modificationCount=0;
    }

    /**
     *
     * @param collection
     * @throws NullPointerException if collection is null
     */
    public ArrayIndexedCollection (Collection collection) throws NullPointerException{
        if(collection == null) throw new NullPointerException();
        this.size=0;
        this.elements=new Object[16];
        this.modificationCount=0;
        this.addAll(collection);

    }

    /**
     *
     * @param collection
     * @param initialCapacity
     * @throws NullPointerException if collection is null
     * @throws IllegalArgumentException if initalcapacity is lower than 1
     */
    public ArrayIndexedCollection(Collection collection,int initialCapacity) throws NullPointerException ,IllegalArgumentException {
        this(initialCapacity);
        if(collection == null) throw new NullPointerException();
        if(collection.size()>initialCapacity){
            this.size=collection.size();
            this.elements=collection.toArray();
        }else{
            this.addAll(collection);}
    }

    /**
     *
     * @return size of this collection
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * adds one element in elements array
     * @param value which is added in the array
     * @throws NullPointerException if value is null
     */
    @Override
    public void add(Object value) throws NullPointerException{
        if(value==null) throw new NullPointerException("Value can not be null");
        this.size++;
        if(this.elements.length<this.size){
            int x=elements.length*2;
            Object[] elem=this.elements.clone();
            elements=new Object[x];
            for(int i =0;i<this.size-1;i++){
                elements[i]=elem[i];
            }
            modificationCount++;
        }
        elements[this.size - 1] = value;

    }

    /**
     * checks if this list contains given value
     * @param value
     * @return true if array contains value and false if it does not
     */
    @Override
    public boolean contains(Object value) {
        for(int i =0;i<this.size;i++){
            if(this.elements[i].equals(value)){
               return true;
            }
        }
        return false;
    }

    /**
     * gives us postition of element in array
     * @param value
     * @return index of given object
     */
    public int indexOf(Object value){
        if(value==null || isEmpty()) return -1;
        for(int i =0;i<this.size;i++){
            if(this.elements[i].equals(value)){
                return i;
            }
        }
        return -1;
    }

    /**
     * removes element from elements array
     * @param value which is removed
     * @return true if value vas removed, false if array did not contain given element
     */
    @Override
    public boolean remove(Object value) {
        if(!this.contains(value)) return false;
        remove(indexOf(value));
        modificationCount++;
        return true;
    }

    /**
     * Creates new instance of an array and copies all elements in it
     * @return new instance od array
     */
    @Override
    public Object[] toArray() {
        Object[] result=new Object[this.size];
        for(int i =0;i<size;i++){
            result[i]=this.elements[i];
        }
        return result;
    }
    /**
     * clears the memory of the array
     */
    @Override
    public void clear() {
        for(int i =0;i<this.size;i++){
            this.elements[i]=null;
        }
        this.size=0;
        modificationCount++;
    }



    /**
     * gets element at array postition index
     * @param index
     * @return elemetn at given position
     * @throws IndexOutOfBoundsException if position is lower than 0 or greater than size-1
     */
    public Object get(int index) throws IndexOutOfBoundsException{
        if(index<0 || index>size-1) throw new IndexOutOfBoundsException();
        else return elements[index];}

    /**
     * inserts value to given position in array
     * @param value
     * @param position
     * @throws IndexOutOfBoundsException if posititon is greater than size and less than 0
     */
    public void insert(Object value, int position) throws IndexOutOfBoundsException{
        if(position<0 || position>size) throw new IndexOutOfBoundsException();
        Object [] array = this.toArray();

        if(position==elements.length){
            add(value);
            return;
        }
        int x=size;
        this.clear();
        for (int i =0;i<=x;i++){
            if(i==position){
                this.add(value);
            }else if(i<position){
               this.add(array[i]);
            }else {
                this.add(array[i-1]);
            }
        }
        array=null;
        modificationCount++;
    }

    /**
     * removes element at given index from array
     * @param index
     * @throws IndexOutOfBoundsException if index is greater than size-1 and lower than 0
     */
    public void remove(int index) throws IndexOutOfBoundsException{
        if(index<0 || index>size-1) throw new IndexOutOfBoundsException();
        for (int i =index;i<this.size-1;i++){
            elements[i]=elements[i+1];
        }
        elements[this.size-1]=null;
        this.size--;
        modificationCount++;
    }
    @Override
    public ElementsGetter createElementsGetter() {
        return new ArrayIndexedCollection.ArrayElementsGetter(this);
    }


    private static class ArrayElementsGetter implements ElementsGetter{
        private int currentPosition;
        private long savedModificationCount;
        ArrayIndexedCollection coll;
        public ArrayElementsGetter(ArrayIndexedCollection coll){
            this.currentPosition=0;
            this.coll=coll;
            this.savedModificationCount=coll.modificationCount;
        }
        @Override
        public Object getNextElement() throws  ConcurrentModificationException{
            if(savedModificationCount!= coll.modificationCount) throw new ConcurrentModificationException();
            if(coll.elements[currentPosition]==null) throw new NullPointerException();
            return  coll.elements[currentPosition++];
        }

        @Override
        public boolean hasNextElement() throws  ConcurrentModificationException{
            if(savedModificationCount!= coll.modificationCount) throw new ConcurrentModificationException();
            return coll.size>currentPosition;
        }
    }
}
