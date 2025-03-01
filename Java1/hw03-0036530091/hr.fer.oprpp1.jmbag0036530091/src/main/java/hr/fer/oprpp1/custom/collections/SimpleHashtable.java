package hr.fer.oprpp1.custom.collections;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Objects;

public class SimpleHashtable <K,V> implements Iterable<SimpleHashtable.TableEntry<K,V>>{
     TableEntry<K, V>[] table;
    private int size;

    private int modificationCount;


    public static class TableEntry<K, V> {
        private K key;
        private V value;
        private TableEntry<K, V> next;

        public TableEntry(K key, V value) {
            if (key == null) throw new NullPointerException();
            this.key = key;
            this.value = value;
            next=null;
        }

        /**
         * @return Key
         */
        public K Key() {
            return key;
        }

        /**
         * @return value
         */
        public V Value() {
            return this.value;
        }

        /**
         * sets value of varibale value to new given value
         *
         * @param value
         */
        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TableEntry<?, ?> that = (TableEntry<?, ?>) o;
            return Objects.equals(key, that.key) && Objects.equals(value, that.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }

        @Override
        public String toString() {
            return  key +
                    "=" + value;
        }
    }

    public SimpleHashtable() {
        this.table = (TableEntry<K, V>[]) new TableEntry[16];
        this.size = 0;
       this.modificationCount=0;

    }

    /**
     * initalizes class at capacity which is power of 2 and is greater than or equal given capacity
     * @param capacity
     * @throws IllegalArgumentException if capacity is less than 1
     */
    public SimpleHashtable(int capacity) throws IllegalArgumentException {
        if (capacity < 1) throw new IllegalArgumentException();
        int trueCapacity = 1;
        while (trueCapacity < capacity) {
            trueCapacity *= 2;
        }
        this.table = (TableEntry<K, V>[]) new TableEntry[trueCapacity];
        this.size = 0;

    }

    /**
     * adds one key value pair to instnace of class
     * @param key
     * @param value
     * @return value
     * @throws NullPointerException if key equals null
     */
    public V put(K key, V value) throws NullPointerException {
        if (key == null) throw new NullPointerException();
        if((Double.valueOf(size)/Double.valueOf(table.length))>=0.75) allocateDouble();
        int slot = Math.abs(key.hashCode()) % table.length;
        if (table[slot] == null) {
            size++;
            modificationCount++;
            table[slot] = new TableEntry<>(key, value);
        } else {
            TableEntry<K, V> tableEntry = table[slot];
            while (tableEntry.next != null) {
                if (key.equals(tableEntry.Key())) {
                    tableEntry.setValue(value);
                    return value;
                }
                tableEntry = tableEntry.next;
            }
            if (key.equals(tableEntry.Key())) {
                tableEntry.setValue(value);
                return value;
            }
            size++;
            modificationCount++;
            tableEntry.next = new TableEntry<>(key, value);
        }
        return value;
    }

    /**
     * allocates array at double the length and puts all elements inside
     */
    private void allocateDouble() {
        int a= table.length;
        TableEntry<K, V>[] table2= this.toArray();
        this.table = (TableEntry<K, V>[]) new TableEntry[2*a];
        for (int i =0;i<size;i++){
            K key=table2[i].key;
            V value = table2[i].value;
            int slot = Math.abs(key.hashCode()) % table.length;
            if (table[slot] == null) {

                table[slot] = new TableEntry<>(key, value);
            } else {
                TableEntry<K, V> tableEntry = table[slot];
                while (tableEntry.next != null) {
                    if (key.equals(tableEntry.Key())) {
                        tableEntry.setValue(value);

                    }
                    tableEntry = tableEntry.next;
                }
                if (key.equals(tableEntry.Key())) {
                    tableEntry.setValue(value);

                }

                tableEntry.next = new TableEntry<>(key, value);
            }
        }

    }

    /**
     * gets value paired with given key
     * @param key
     * @return
     */
    public V get(Object key) {
        if (key == null) return null;
        else {
            int slot = Math.abs(key.hashCode()) % table.length;
            TableEntry<K, V> tableEntry = table[slot];
            if (table[slot] == null) return null;
            while (tableEntry.next != null) {
                if (key.equals(tableEntry.Key())) {
                    return tableEntry.Value();
                }
                tableEntry = tableEntry.next;
            }
            if (key.equals(tableEntry.Key())) {
                return tableEntry.Value();
            }


        }
        return null;

    }

    /**
     *
     * @return number of pairs stored in array
     */
    public int size() {
        return this.size;
    }

    /**
     *
     * @param key
     * @return true if map contains key and false otherwise
     */
    public boolean containsKey(Object key) {
        if (key == null) return false;
        else {
            int slot = Math.abs(key.hashCode()) % table.length;
            TableEntry<K, V> tableEntry = table[slot];
            if (table[slot] == null) return false;
            while (tableEntry.next != null) {
                if (key.equals(tableEntry.Key())) {
                    return true;
                }
                tableEntry = tableEntry.next;
            }
            if (key.equals(tableEntry.Key())) {
                return true;
            }


        }
        return false;

    }

    /**
     *
     * @param value
     * @return true if map contains value and false otherwise
     */
    public boolean containsValue(Object value) {
        if(value==null){
            for (int i = 0; i < table.length; i++) {
                if (table[i] != null) {
                    TableEntry<K, V> tableEntry = table[i];
                    if (tableEntry.Value()==null) {
                        return true;
                    } else {
                        while (tableEntry.next != null) {
                            if (tableEntry.Value()==null)
                                return true;
                            tableEntry = tableEntry.next;
                        }
                        if (tableEntry.Value()==null)
                            return true;

                    }
                }
            }
        }else {
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                TableEntry<K, V> tableEntry = table[i];
                if(tableEntry.value!=null) {
                if (tableEntry.Value().equals(value)) {
                    return true;
                } }
                    while (tableEntry.next != null) {
                        if (tableEntry.Value().equals(value))
                            return true;
                        tableEntry = tableEntry.next;
                    }
                    if(tableEntry.value!=null) {
                        if (tableEntry.value.equals(value))
                            return true;
                    }

            }
        }}
        return false;
    }

    /**
     * removes pair wtih given key
     * @param key
     * @return value
     */
    public V remove(Object key) {
        if (key == null) return null;
        else {
            int slot = Math.abs(key.hashCode()) % table.length;
            TableEntry<K, V> tableEntry = table[slot];
            TableEntry<K, V> pomocni = table[slot];
            if(!containsKey(key))return null;
            if (key.equals(tableEntry.Key())) {
                V value = tableEntry.Value();
                table[slot] = tableEntry.next;
                tableEntry = null;
                size--;
                modificationCount++;
                return value;
            }
            while (tableEntry.next != null) {
                if (key.equals(tableEntry.Key())) {
                    V value = tableEntry.Value();
                    pomocni.next = tableEntry.next;
                    tableEntry = null;
                    size--;
                    modificationCount++;
                    return value;
                }
                pomocni = tableEntry;
                tableEntry = tableEntry.next;
            }
            if (key.equals(tableEntry.Key())) {
                V value = tableEntry.Value();
                pomocni.next = tableEntry.next;
                tableEntry = null;
                size--;
                modificationCount++;
                return value;
            }


        }
        return null;


    }

    /**
     *
     * @return true if map is empty and false otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * @return to String
     */
    public String toString() {
        if(size==0){
            return "[]";
        }
        TableEntry<K, V>[] array=this.toArray();
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append('[');
        for(int i=0;i<size-1;i++){
            stringBuilder.append(array[i].toString()+",");
        }
        stringBuilder.append(array[size-1].toString()+"]");
        return stringBuilder.toString();
    }

    /**
     * @return newly allocated array of elements from map
     */
    public TableEntry<K, V>[] toArray() {
        TableEntry<K, V>[] array = (TableEntry<K, V>[]) new TableEntry[size];
        int counter=0;
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                TableEntry<K, V> tableEntry = table[i];
                while (tableEntry != null) {
                    array[counter++]=tableEntry;
                    tableEntry = tableEntry.next;
                }

            }
        }
        return array;
    }

    /**
     * clears memory from the array
     */
    public void clear(){
        for(int i=0;i<table.length;i++){
            table[i]=null;
        }
        size=0;
    }
    @Override
    public Iterator<TableEntry<K, V>> iterator() {
        return new IteratorImpl();
    }
    private class IteratorImpl implements Iterator<SimpleHashtable.TableEntry<K,V>> {
        int  IteratorModificationCount;
        int arrayCount;
        TableEntry<K, V> current;
        TableEntry<K, V> pom;
        boolean wasRemoved=false;
        int initalSize;
        public IteratorImpl() {

            this.arrayCount=0;
            this.IteratorModificationCount = modificationCount;
            current=null;

            this.initalSize=size;

        }

        public boolean hasNext() {
            if(modificationCount!=IteratorModificationCount) throw new ConcurrentModificationException();
            return 0<initalSize;
        }
        public TableEntry<K,V> next() {

            if(current==null){
                while (table[arrayCount]==null){
                    arrayCount++;
                }
                current=table[arrayCount];
                arrayCount++;
            } else if (current.next==null) {
                while (table[arrayCount]==null){
                    arrayCount++;
                }
                current=table[arrayCount];
                arrayCount++;
            } else {
                current=current.next;
            }
           initalSize--;
            wasRemoved=false;

            return current;

        }
        public void remove() {
            if(wasRemoved) throw new IllegalStateException();
            wasRemoved=true;

            K key = current.key;
            int slot = Math.abs(key.hashCode()) % table.length;


            TableEntry<K, V> tableEntry = table[slot];
            TableEntry<K, V> pomocni = table[slot];
            if (key.equals(tableEntry.Key())) {
                V value = tableEntry.Value();
                table[slot] = tableEntry.next;
                tableEntry = null;
                initalSize--;//size--;
                return;
            }
            while (tableEntry.next != null) {
                if (key.equals(tableEntry.Key())) {
                    V value = tableEntry.Value();
                    pomocni.next = tableEntry.next;
                    tableEntry = null;
                    initalSize--;;
                    return;
                }
                pomocni = tableEntry;
                tableEntry = tableEntry.next;
            }
            if (key.equals(tableEntry.Key())) {
                V value = tableEntry.Value();
                pomocni.next = tableEntry.next;
                tableEntry = null;
                initalSize--;;
                return;
            }


        }
    }

}
