package hr.fer.oprpp1.custom.collections;

import java.util.Objects;

public class Dictionary<K,V> {
    private static class Pair<K,V> {
       K key;
       V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public Pair(K key) {
            this.key = key;
            this.value = null;
        }

        /**
         *
         * @return Key
         */
        public K Key() {
            return key;
        }

        /**
         *
         * @return value
         */
        public V Value() {
            return value;
        }

        /**
         * sets value of varibale value to new given value
         * @param value
         */
        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(key, pair.key) && Objects.equals(value, pair.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }
    ArrayIndexedCollection<Pair<K,V>> coll;

    /**
     * constructor of this class
     */
    public Dictionary() {
        this.coll = new ArrayIndexedCollection<>();
    }

    /**
     *
     * @return true if dictionary is empty and false otherwise
     */
    public boolean isEmpty(){
        return coll.isEmpty();
    }

    /**
     *
     * @return number of pairs stored in dictionary
     */
    public int size(){
        return coll.size();
    }

    /**
     * clears the memory of dictionary
     */
    public void clear(){
        coll.clear();
    }

    /**
     * puts key and value pair in dictionary or puts new value
     * if key already exists in dictionary
     * @param key
     * @param value
     * @return value
     */
    public V put(K key, V value){
        Pair<K,V> pair=new Pair<>(key, value);
        boolean wasInserted=false;
        for(int i=0;i<coll.size();i++){
            if(coll.get(i).Key().equals(key)){
               coll.get(i).setValue(value);
               wasInserted=true;
            }
        }
        if(!wasInserted){
            coll.add(pair);
        }
        return value;

    }

    /**
     *
     * @param key of key-value pair
     * @return value paired with this key or null if dictionry does not contain key
     */
    public V get(Object key){
        for(int i=0;i<coll.size();i++){
            if(coll.get(i).Key().equals(key)){
                return coll.get(i).Value();
            }
        }

        return null;
    }

    /**
     * removes key value pair which is defined by given key
     * @param key
     * @return value which is removed
     */
    public V remove(K key){
        V value=this.get(key);
         Pair<K,V> pair=new Pair<>(key,value);
         coll.remove(pair);
         return value;

    }
}
