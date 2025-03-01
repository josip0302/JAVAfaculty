package hr.fer.oprpp1.custom.collections;

public interface Collection<T>{
    /**
     * if collection is empty returns true else it is false
     * @return boolean
     */
    public default boolean isEmpty(){
        return size()==0;
    }

    /**
     *
     * @return size of the collection
     */
    public int size();

    /**
     * adds value to collecton
     * @param value
     */
    public void add(T value);

    /**
     *
     * @param value
     * @return true if collection contains value and false otherwise
     */
    public boolean contains(Object value);

    /**
     * removes element of given value from collection
     * @param value
     * @return true if element was removed and false otherwise
     */
    public boolean remove(T value);

    /**
     *
     * @return new instance od array with all elements from collection
     */
    public T[] toArray();

    /**
     * processes all elements of collection
     * @param processor
     */
    public default void forEach(Processor<T> processor){
        ElementsGetter<T> getter=this.createElementsGetter();
       getter.processRemaining(processor);
    }

    /**
     * adds collection other to this collection
     * @param other
     */
    public default void addAll(Collection<T> other){
        other.forEach(value->this.add((T) value));
    }


    public void clear();

    /**
     *
     * @return elementsgetter instance
     */
    ElementsGetter createElementsGetter();

    public default void addAllSatisfying(Collection<? extends T> col, Tester<? super T> tester){
        ElementsGetter getter=col.createElementsGetter();
       getter.processRemaining((x)->{if(tester.test((T) x))this.add((T) x);});

    }
}
