package hr.fer.oprpp1.custom.collections;

public interface List extends Collection {
    /**
     *
     * @param index
     * @return element of given index from list
     */
    Object get(int index);

    /**
     * inserts value at given position in list
     * @param value
     * @param position
     */
    void insert(Object value, int position);

    /**
     *
     * @param value
     * @return position of the element in list which has given value
     */
    int indexOf(Object value);

    /**
     * removes element from position index in list
     * @param index
     */
    void remove(int index);

}
