package hr.fer.oprpp1.custom.collections;

public class LinkedListIndexedCollection extends Collection {
    private static class ListNode {
        public Object storage;
        public ListNode previous;
        public ListNode next;
    }

    private ListNode first;
    private ListNode last;
    private int size;

    public LinkedListIndexedCollection() {
        super();
        this.first = this.last = null;
        this.size = 0;

    }

    public LinkedListIndexedCollection  (Collection coll) throws NullPointerException{
        if (coll == null) throw new NullPointerException();
        this.addAll(coll);

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void add(Object value) {
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
        size++;

    }

    @Override
    public boolean contains(Object value) throws NullPointerException{
        if(value==null) throw new NullPointerException();
        ListNode node = first;
        for (int i = 0; i < this.size; i++) {
           if((node.storage).equals(value)) return true;
            node = node.next;
        }
        return false;

    }

    @Override
    public boolean remove(Object value) {
        if(value==null) return false;
        ListNode node = first;
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

                    ListNode after = node.next;
                    ListNode before = node.previous;
                    after.previous = before;
                    before.next = after;
                    node = null;
                    size--;


                    }
                return true;
                }
            node = node.next;
            }
        return false;
        }


    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        ListNode node = first;
        for (int i = 0; i < this.size; i++) {
            result[i] = node.storage;
            node = node.next;
        }
        return result;
    }

    @Override
    public void forEach(Processor processor) {
        ListNode node = first;
        for (int i = 0; i < this.size; i++) {
           processor.process(node.storage);
           ListNode pom=new ListNode();
           node = node.next;
        }
    }

    @Override
    public void addAll(Collection other) {
        Processor pro=new Processor(){
            @Override
            public void process(Object value){
                add(value);

            }
        };
        other.forEach(pro);
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        this.size = 0;
    }

    public Object get(int index) throws IndexOutOfBoundsException{
        if (index < 0 || index > size - 1) throw new IndexOutOfBoundsException();
        if (index < (size / 2)) {
            ListNode node = first;
            for (int i = 0; i <= size / 2; i++) {
                if (i == index) {
                    return node.storage;
                }
                node = node.next;

            }
        } else {
            ListNode node = last;
            for (int i = size-1; i >= size / 2; i--) {
                if (i == index) {
                    return node.storage;
                }
                node=node.previous;
            }
        }
        return null;//stoji samo da intelliJ ne baca gresku.
    }
    public int indexOf(Object value){
        ListNode node = first;
        for (int i = 0; i < this.size; i++) {
            if((node.storage).equals(value)) return i;
            node = node.next;
        }
        return -1;
    }
    public void remove(int index) throws IndexOutOfBoundsException{
        if (index < 0 || index > size - 1) throw new IndexOutOfBoundsException();
        ListNode node = first;
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
                    ListNode after = node.next;
                    ListNode before = node.previous;
                    after.previous = before;
                    before.next = after;
                    node = null;
                    size--;
                    return;
                }
                node = node.next;
            }
        }

    }
    public void insert(Object value, int position) throws IndexOutOfBoundsException,NullPointerException{
        if (position < 0 || position > size) throw new IndexOutOfBoundsException();
        if(value==null) throw new NullPointerException();
        ListNode listNode=new ListNode();
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

    }

}
