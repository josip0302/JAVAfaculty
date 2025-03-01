package hr.fer.oprpp1.custom.collections;

public interface ElementsGetter {
    /**
     *
     * @return next element object
     */
    public Object getNextElement();

    /**
     *
     * @return if next element exists and false otherwise
     */
    public boolean hasNextElement();

    /**
     * processes all leftover elements
     * @param p
     */
    public default void processRemaining(Processor p){
       do{
            p.process(getNextElement());
        }while (hasNextElement());
    }
}
