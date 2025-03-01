package hr.fer.oprpp1.custom.collections;

public interface Collection {
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
    public void add(Object value);

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
    public boolean remove(Object value);

    /**
     *
     * @return new instance od array with all elements from collection
     */
    public Object[] toArray();

    /**
     * processes all elements of collection
     * @param processor
     */
    public default void forEach(Processor processor){
        ElementsGetter getter=this.createElementsGetter();
       getter.processRemaining(processor);
    }

    /**
     * adds collection other to this collection
     * @param other
     */
    public default void addAll(Collection other){
        other.forEach((value)->{this.add(value);});
    }
    public void clear();

    /**
     *
     * @return elementsgetter instance
     */
    ElementsGetter createElementsGetter();
    public default void addAllSatisfying(Collection col, Tester tester){
        ElementsGetter getter=col.createElementsGetter();
       getter.processRemaining((x)->{if(tester.test(x))this.add(x);});

    }
}
