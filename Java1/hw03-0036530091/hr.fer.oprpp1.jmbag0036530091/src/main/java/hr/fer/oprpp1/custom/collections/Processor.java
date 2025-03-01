package hr.fer.oprpp1.custom.collections;

public interface Processor<T> {
    /**
     * processes object of given value
     * @param value
     */
    public void process(T value);
}
