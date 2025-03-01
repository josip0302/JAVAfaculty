package hr.fer.oprpp1.custom.collections;

import java.util.ConcurrentModificationException;

public class LinkedListIndexedCollection<T> implements List<T> {
    private static class ListNode<T> {
        public T storage;
        public ListNode<T> previous;
        public ListNode<T> next;
    }

    private ListNode<T> first;
    private ListNode<T> last;
    private int size;
    private long modificationCount;
    public LinkedListIndexedCollection() {
        this.first = this.last = null;
        this.size = 0;
        this.modificationCount=0;
    }

    /**
     * creates new list from given collection coll
     * @param coll
     * @throws NullPointerException if coll is null
     */
    public LinkedListIndexedCollection  (Collection<T> coll) throws NullPointerException{
        if (coll == null) throw new NullPointerException();
        this.modificationCount=0;
        this.addAll(coll);

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
     * adds object value to this collection(to elements array
     * @param value
     */
    @Override
    public void add(T value) {
        if (value == null) throw new NullPointerException();
        if (first == null) {
            first=new ListNode();
            first.storage = value;
            first.next=null;
        } else if(last==null) {
            last =new ListNode();
            first.next = last;
            last.previous = first;
            last.storage=value;
        }
        else{
            ListNode node = new ListNode();
            node.previous = last.previous;
            node.storage = last.storage;
            node.previous.next=node;
            node.next = last;
            last.previous = node;
            last.next = null;
            last.storage = value;
        }
        modificationCount++;
        size++;

    }

    /**
     *
     * @param value
     * @return true if collection contains object value else returns false
     * @throws NullPointerException if value is null
     */
    @Override
    public boolean contains(Object value) throws NullPointerException{
        if(value==null) throw new NullPointerException();
        ListNode<T> node = first;
        for (int i = 0; i < this.size; i++) {
           if((node.storage).equals(value)) return true;
            node = node.next;
        }
        return false;

    }

    /**
     *
     * @param value which we want to remove
     * @return true if element is removed and false if element is not
     */
    @Override
    public boolean remove(T value) {
        if(value==null) return false;
        ListNode<T> node = first;
        for (int i = 0; i < this.size; i++) {
            if((node.storage).equals(value)) {
                if(i==this.size-1){
                    last=last.previous;
                    last.next=null;
                    size--;
                }else if(i ==0){
                    first=first.next;
                    first.previous=null;
                    size--;
                }else {

                    ListNode<T> after = node.next;
                    ListNode<T> before = node.previous;
                    after.previous = before;
                    before.next = after;
                    node = null;
                    size--;


                    }
                modificationCount++;
                return true;
                }
            node = node.next;
            }
        return false;
        }

    /**
     *
     * @return new instance of array with values from this collection
     */
    @Override
    public T[] toArray() {
       T[] result = (T[]) new Object[size];
        ListNode<T> node = first;
        for (int i = 0; i < this.size; i++) {
            result[i] = node.storage;
            node = node.next;
        }
        return result;
    }


    /**
     * clears memory of this collection
     */
    @Override
    public void clear() {
        first = null;
        last = null;
        this.size = 0;
        modificationCount++;
    }

    /**
     *
     * @param index of element we want to get
     * @return element at a given index
     * @throws IndexOutOfBoundsException if index is graeter than size-1 or lower than 0
     */
    public T get(int index) throws IndexOutOfBoundsException{
        if (index < 0 || index > size - 1) throw new IndexOutOfBoundsException();
        if (index <= (size / 2)) {
            ListNode<T> node = first;
            for (int i = 0; i <= size / 2; i++) {
                if (i == index) {
                    return node.storage;
                }
                node = node.next;

            }
        } else {
            ListNode<T> node = last;
            for (int i = size-1; i >= size / 2; i--) {
                if (i == index) {
                    return node.storage;
                }
                node=node.previous;
            }
        }
        return null;//stoji samo da intelliJ ne baca gresku.
    }

    /**
     *
     * @param value
     * @return index of given element and -1 if collection does not contain given value
     */
    public int indexOf(T value){
        ListNode<T> node = first;
        for (int i = 0; i < this.size; i++) {
            if((node.storage).equals(value)) return i;
            node = node.next;
        }
        return -1;
    }

    /**
     * removes value at given index
     * @param index
     * @throws IndexOutOfBoundsException if index is graeter than size-1 or lower than 0
     */
    public void remove(int index) throws IndexOutOfBoundsException{
        if (index < 0 || index > size - 1) throw new IndexOutOfBoundsException();
        ListNode<T> node = first;
        if(index==this.size-1){
            last=last.previous;
            last.next=null;
            size--;
        }else if(index ==0){
            first=first.next;
            first.previous=null;
            size--;
        }else {
            for (int i = 0; i < this.size; i++) {
                if (i == index) {
                    ListNode<T> after = node.next;
                    ListNode<T> before = node.previous;
                    after.previous = before;
                    before.next = after;
                    node = null;
                    size--;
                    return;
                }
                node = node.next;
            }
        }
        modificationCount++;

    }

    /**
     *
     * @param value value which we want to insert
     * @param position position in array where we want to insert value
     * @throws IndexOutOfBoundsException if postition is greater than size or lower than 0
     * @throws NullPointerException if value is null
     */
    public void insert(T value, int position) throws IndexOutOfBoundsException,NullPointerException{
        if (position < 0 || position > size) throw new IndexOutOfBoundsException();
        if(value==null) throw new NullPointerException();
        ListNode<T> listNode=new ListNode();
        if(position==0){
            listNode.next=first.next;
            listNode.storage=first.storage;
            first.storage=value;
            first.next=listNode;
            listNode.previous=first;

        }else if(position==size){
            listNode.previous=last.previous;
            listNode.storage=last.storage;
            last.storage=value;
            listNode.next=last;
            last.previous=listNode;
        }else {
            ListNode node = first;
            for (int i = 0; i < position; i++) {
                node = node.next;
            }
            listNode.storage=value;
            listNode.previous=node.previous;
            node.previous.next=listNode;
            node.previous=listNode;
            listNode.next=node;
        }
        size++;
        modificationCount++;

    }
    @Override
    public ElementsGetter createElementsGetter() {
        return new LinkedListElementsGetter<T>(this);
    }
    private static class LinkedListElementsGetter<T> implements ElementsGetter<T>{
        private ListNode currentPosition;
        private LinkedListIndexedCollection<T> coll;
        private long savedModificationCount;
        public LinkedListElementsGetter(LinkedListIndexedCollection<T> coll){
            this.currentPosition= coll.first;
            this.coll=coll;
            savedModificationCount=coll.modificationCount;

        }
        @Override
        public T getNextElement() {
            if(savedModificationCount!= coll.modificationCount) throw new ConcurrentModificationException();
            ListNode<T> result=currentPosition;
            currentPosition=currentPosition.next;
            return  result.storage;
        }

        @Override
        public boolean hasNextElement() {
            if(savedModificationCount!= coll.modificationCount) throw new ConcurrentModificationException();
            return currentPosition!=null;
        }
    }

}
