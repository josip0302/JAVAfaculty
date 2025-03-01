package hr.fer.oprpp1.custom.collections;

public interface List<T> extends Collection<T> {
    /**
     *
     * @param index
     * @return element of given index from list
     */
    T get(int index);

    /**
     * inserts value at given position in list
     * @param value
     * @param position
     */
    void insert(T value, int position);

    /**
     *
     * @param value
     * @return position of the element in list which has given value
     */
    int indexOf(T value);

    /**
     * removes element from position index in list
     * @param index
     */
    void remove(int index);

}
